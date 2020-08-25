package cn.csfz.tariff;


import cn.csfz.tariff.model.Tariff;
import cn.csfz.tariff.service.ITariffService;
import cn.csfz.tariff.util.OkHttpUtils;
import com.alibaba.fastjson.JSONObject;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@RestController
@Slf4j
public class Controller {


    private static String URL = "http://www.hscode.net/IntegrateQueries/YsInfoPager?pageIndex=";
    private static int SIZE = 2401;

    @Autowired
    ITariffService ITariffService;

    @Autowired
    OkHttpUtils okHttpUtils;

    @RequestMapping("tariff")
    public Boolean tariff(int index) throws IOException {


//        Flux.range(index,SIZE).map(this::getDocument).map(this::documentToTariff).collect((Supplier<ArrayList<Tariff>>) ArrayList::new, ArrayList::addAll).map(tariffs -> ITariffService.saveBatch(tariffs)).block();
        return Flowable.range(index, SIZE).map(number -> Jsoup.connect(URL + number).post()).map(this::documentToTariff).collect((Callable<List<Tariff>>) ArrayList::new, List::addAll).map(tariffs -> ITariffService.saveBatch(tariffs)).blockingGet();
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
            tariff.setExportTariffRate(element.child(5).children().get(3).text());
            tariff.setExportRebateRate(element.child(5).children().get(5).text());
            tariff.setVatRate(element.child(6).children().get(1).text());
            tariff.setCustomsSupervisionConditions(element.child(6).children().get(3).text());
            tariff.setInspectionAndQuarantineCategory(element.child(6).children().get(5).text());
            tariffs.add(tariff);
            log.error(JSONObject.toJSONString(tariffs));
        }
        return tariffs;
    }



    private  Document  getDocument(Integer number) {
        try {
            return Jsoup.connect(URL+number).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
