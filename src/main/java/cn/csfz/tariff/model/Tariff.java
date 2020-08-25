package cn.csfz.tariff.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


@EqualsAndHashCode
@Data
@TableName("zf_tariff")
public class Tariff {

    /**主键*/
    @TableId(type = IdType.ID_WORKER_STR)
    @TableField(value ="zf_change_log.id")
    private java.lang.String id;
    /**创建人*/
    @TableField(value ="zf_change_log.create_by")
    private java.lang.String createBy;
    /**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value ="zf_change_log.create_time")
    private java.util.Date createTime;
    /**更新人*/
    @TableField(value ="zf_change_log.update_by")
    private java.lang.String updateBy;
    /**更新时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value ="zf_tariff.update_time")
    private java.util.Date updateTime;
    //商品编码
    @TableField(value ="zf_tariff.hs_code")
    private String hsCode;
    //商品名称
    @TableField(value ="zf_tariff.name")
    private String name;
    //申报要素
    @TableField(value ="zf_tariff.items")
    private String items;
    //第一法定单位
    @TableField(value ="zf_tariff.unit_1")
    private String unit1;
    //第二法定单位
    @TableField(value ="zf_tariff.unit_2")
    private String unit2;
    //最惠国进口税率
    @TableField(value ="zf_tariff.mfn_import_rate")
    private String mfnImportRate;
    //普通进口税率
    @TableField(value ="zf_tariff.general_import_rate")
    private String generalImportRate;
    //暂定进口税率
    @TableField(value ="zf_tariff.tentative_import_rate")
    private String tentativeImportRate;
    //消费税率
    @TableField(value ="zf_tariff.consumption_rate")
    private String consumptionRate;
    //进口关税率
    @TableField(value ="zf_tariff.export_tariff_rate")
    private String exportTariffRate;
    //出口退税率
    @TableField(value ="zf_tariff.export_rebate_rate")
    private String exportRebateRate;
    //增值税率
    @TableField(value ="zf_tariff.vat_rate")
    private String vatRate;
    //海关监管条件
    @TableField(value ="zf_tariff.customs_supervision_conditions")
    private String customsSupervisionConditions;
    //检验检疫类别
    @TableField(value ="zf_tariff.inspection_and_quarantine_category")
    private String inspectionAndQuarantineCategory;
}
