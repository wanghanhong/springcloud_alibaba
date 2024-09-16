package com.smart.card.cardapi.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlowParamVo extends CmpParamVo{
    private static final long serialVersionUID = -97522868445947073L;

    // 后向流量池号码--例如：50LLC00000
    private String poolNbr;
    // 查询页码--例如1，表示第一页，每页最多50条记录
    private String currentPage;
    // 流量池成员号码 1064****000	例如：1064900000000
    private String member_accNbr;
    // 流量池流量限额	 例如：1，只能设置为-1,0或正整数,当设置为-1时，表示无限制
    private String flow_quota;
    // 充值的流量值	String	 charge值只能传正整数，充值单位为GB
    private String charge;
    // monthSelect	查询月份	String	Y	1	1表示当月，0表示上月
    private String monthSelect;
    // pageIndex	当前页数	String	Y	1	每页包含50条记录，页码从1开始。
    private String pageIndex;
    // billingCycleID	账期	String	Y	201801	账期
    private String billingCycleID;


}
