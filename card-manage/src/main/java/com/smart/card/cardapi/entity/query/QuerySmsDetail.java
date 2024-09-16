package com.smart.card.cardapi.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuerySmsDetail implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
    * 2016/09/06 16:25:00
    */

    private String start_time;
    /**
    * 0.1
    */

    private String ticket_charge;
    /**
    * 14910000000
    */

    private String acc_nbr;
    /**
    * 其他业务
    */

    private String ticket_type;
    /**
    * MO（上行）
    */

    private String ticket_type_id;

}
