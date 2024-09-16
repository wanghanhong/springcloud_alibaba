package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 设备类别字典
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_dict")
@ApiModel(value = "TBaseDict对象", description = "设备类别字典")
public class TBaseDict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @ApiModelProperty(value = "字典ID")
    private Integer dictId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典类别")
    private String dictTypeId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sortno;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;


}
