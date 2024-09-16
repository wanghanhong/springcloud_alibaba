package com.smart.brd.sys.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pano
 */
@Data
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    private int pageSize = 10;
    private int pageNum = 1;

    private String sortField;
    private String sortOrder;
}
