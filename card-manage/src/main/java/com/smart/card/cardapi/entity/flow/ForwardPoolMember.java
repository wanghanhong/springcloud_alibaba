package com.smart.card.cardapi.entity.flow;

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
public class ForwardPoolMember implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 总页数
     */
    private String totalPage;

    /**
     * 当前页数
     */
    private String pageIndex;

    /**
     * 流量池群号
     */
    private String mainNumber;

    /**
     * 符合条件的列表
     */
    private List<ForwardPoolList> list;
}
