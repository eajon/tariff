package cn.csfz.tariff.model;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
public class Tariff {
    //商品编码
    private String hsCode;
    //商品名称
    private String name;
    //申报要素
    private String items;
    //第一法定单位
    private String unit1;
    //第二法定单位
    private String unit2;
    //最惠国进口税率
    private String mfnImportRate;
    //普通进口税率
    private String generalImportRate;
    //暂定进口税率
    private String tentativeImportRate;
    //消费税率
    private String consumptionRate;
    //进口关税率
    private String importTariffRate;
    //出口退税率
    private String exportRebateRate;
    //增值税率
    private String vatRate;
    //海关监管条件
    private String customsSupervisionConditions;
    //检验检疫类别
    private String inspectionAndQuarantineCategory;
}
