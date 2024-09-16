package com.smart.fire.platform.web.entity.vo;

import lombok.Data;

/**
 * @author: wueryong
 * @Date: 2020/6/9 15
 * @Description:
 */
@Data
public class WholeProvinceVo {

    /**
     * 城市名称
     */
    private String name;

    /**
     * 健康指数
     */
    private Integer healthIndex;
    /**
     * 安装设备总量
     */
    private InstallTotal installTotal;
    /**
     * 设备报警信息
     */
    private DeviceFaultInfo deviceFaultInfo;

    private DeviceFaultInfo faultInfo;
}
