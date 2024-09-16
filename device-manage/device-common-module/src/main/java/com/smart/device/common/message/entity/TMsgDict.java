package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_msg_dict")
public class TMsgDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    private Integer dictId;

    /**
     * 字典名称
     */
    private Integer dictName;

    /**
     * 字典类别
     */
    private Integer dictTypeId;

    /**
     * 排序
     */
    private Integer sortno;

    /**
     * 删除标识(0 正常 1删除)
     */
    private Integer deleteFlag;


}
