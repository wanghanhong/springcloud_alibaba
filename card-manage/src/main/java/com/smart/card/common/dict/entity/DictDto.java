package com.smart.card.common.dict.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 设备类别字典
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dictId;
    private String dictName;

}
