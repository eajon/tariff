package cn.csfz.tariff;


import cn.csfz.tariff.model.Tariff;
import cn.csfz.tariff.util.OkHttpUtils;
import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class Controller {


    @Autowired
    OkHttpUtils okHttpUtils;

    @RequestMapping("tariff")
    public List<Tariff> tariff(int index) throws IOException {
        List<Tariff> tariffList = new ArrayList<>();
        Observable.range(index, 1).map(number -> Jsoup.connect("http://www.hscode.net/IntegrateQueries/YsInfoPager?pageIndex=" + number).post()).map(document -> documentToTariff(document)).subscribe(tariffs -> tariffList.addAll(tariffs));
        return tariffList;
    }


    private List<Tariff> documentToTariff(Document document) {
        Elements elements = document.getElementsByClass("scx_item");
        List<Tariff> tariffs = new ArrayList<>();
        for (Element element : elements) {
            Tariff tariff = new Tariff();
            tariff.setHsCode(element.child(0).select(".even").text());
            tariff.setName(element.child(1).select(".even").text());
            tariff.setItems(element.child(2).select(".even").text());
            tariff.setUnit1(element.child(3).children().get(1).text());
            tariff.setUnit2(element.child(3).children().get(3).text());
            tariff.setMfnImportRate(element.child(4).children().get(1).text());
            tariff.setGeneralImportRate(element.child(4).children().get(3).text());
            tariff.setTentativeImportRate(element.child(4).children().get(5).text());
            tariff.setConsumptionRate(element.child(5).children().get(1).text());
            tariff.setImportTariffRate(element.child(5).children().get(3).text());
            tariff.setExportRebateRate(element.child(5).children().get(5).text());
            tariff.setVatRate(element.child(6).children().get(1).text());
            tariff.setCustomsSupervisionConditions(element.child(6).children().get(3).text());
            tariff.setInspectionAndQuarantineCategory(element.child(6).children().get(5).text());
            tariffs.add(tariff);
        }
        return tariffs;
    }


}
