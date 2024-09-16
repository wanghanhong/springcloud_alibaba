package com.smart.fire.platform.web.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wueryong
 * @Date: 2020/6/9 16
 * @Description: 设备实时故障报警信息
 */
@Data
public class DeviceFaultInfo {

    // 报警数据
    private List<Integer[]> data = new ArrayList<>();

    private List<Integer> deviceType = new ArrayList<>();

    private List<String> deviceTypeName = new ArrayList<>();

    private List<String> faultName = new ArrayList<>();



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
