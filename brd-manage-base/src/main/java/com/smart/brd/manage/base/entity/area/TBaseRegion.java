package com.smart.brd.manage.base.entity.area;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("t_region")
public class TBaseRegion implements Serializable {

    private static final long serialVersionUID = -7790334862410409056L;

    @ApiModelProperty(value = "编码")
    private String regionCode;

    private String parentRegionCode;
    @ApiModelProperty(value = "名称")
    private String regionName;

    private String remarks;

    @TableField(exist = false )
    private String level;

}