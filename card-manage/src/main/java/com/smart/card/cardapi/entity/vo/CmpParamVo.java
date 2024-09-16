package com.smart.card.cardapi.entity.vo;

import lombok.Data;

@Data
public class CmpParamVo {
    private static final long serialVersionUID = -97522868445947073L;

    private String method;
    private String url;

    private String accessNumber;
    private String iccid;
    private String paramAdd;

    private String monthDate;
    private String type;
    private String imsi;
    private String imei;
    private String needDtl;
    private String custId;
    private String bindType;
    private String orderTypeId;
    private String groupId;
    private String simStatus;
    private String queryFlag;
    private String startTime;
    private String endTime;
    private String action;
    private String queryDropCard;
    private String quota;
    private String flowValue;
    private String requestId;
    private String pageIndex;
    private String orderNumber;
    private String bankSub;
    private String fundType;
    private String payMoney;
    private String callUrl;
    private String callBackUrl;
}
