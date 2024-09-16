package com.smart.card.cardapi.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ValueAddedDetail implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
    * [计费号码][业务名称][订购/使用时间][费用类别][时长(秒)][费用（元）]
    */

    private String field;
    /**
    * 0
    */

    private String totalcount;
    /**
    * 010
    */

    private String province_id;
    /**
    * 0
    */

    private String acc_nbr;
    /**
    * 0
    */

    private String totalpage;
    /**
    * 0
    */

    private String service_result_code;
    /**
    * {FEE:0}
    */

    private String total_info;
    /**
    * 
    */

    private String result_data;
    /**
    * 
    */

    private String respcount;
    /**
    * 500000001
    */

    private String mvno_id;

}
