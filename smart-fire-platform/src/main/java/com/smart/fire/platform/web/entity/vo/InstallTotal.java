package com.smart.fire.platform.web.entity.vo;

import lombok.Data;

/**
 * @author: wueryong
 * @Date: 2020/6/9 16
 * @Description: 设备安装总量
 */
@Data
public class InstallTotal {

    /**
     * 烟感器
     */
    private int deviceSmoke;

    /**
     * 水压表
     */
    private int deviceWaterpress;

    /**
     * 液位计
     */
    private int deviceLiquidlevel;
    /**
     * 气体检测器
     */
    private int deviceGas;
    /**
     * 智慧电箱
     */
    private int deviceElectric;

}
