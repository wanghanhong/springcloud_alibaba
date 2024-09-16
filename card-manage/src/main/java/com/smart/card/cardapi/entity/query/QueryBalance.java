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
public class QueryBalance implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
    * 0
    */

    private String accountbalance;
    /**
    * 0
    */

    private String iresult;
    /**
    * 14914040014
    */

    private String number;
    /**
    * 1000000252201608059807830516
    */

    private String group_transactionid;
    /**
    * 0
    */

    private String userbalance;

    /**
    * 0
    */

    private String balance;




}
