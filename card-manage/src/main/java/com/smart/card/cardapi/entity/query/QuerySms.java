package com.smart.card.cardapi.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuerySms implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     *合计话费
     */
    private String charge_cnt_ch;

    /**
     * 处理结果代码
     */
    private String iresult;

    /**
     * 短信使用记录
     */
    private String totalcount;

    /**
     * 流量分页数
     */
    private String totalpage;

    /**
     * 流水号
     */
    private String group_transactionid;

    /**
     * 接入号
     */
    private String number;

    /**
     * 细节列表
     */
    private List<QuerySmsDetail> details;
}
