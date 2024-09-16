package com.smart.device.common.access.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoType{

        /*
        * 主键id
        * */
        @ApiModelProperty(value = "id")
        private Long id;

        /*
        * 解析后的部分id
        * */
        @ApiModelProperty(value = "部位id")
        private String partId;

        /*
        * 类型id
        * */
        @ApiModelProperty(value = "设备类型")
        private Integer deviceType;


}
