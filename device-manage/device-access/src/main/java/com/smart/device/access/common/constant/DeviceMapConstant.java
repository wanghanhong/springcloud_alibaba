package com.smart.device.access.common.constant;

import com.smart.device.access.service.impl.TDeviceCamerasServiceImpl;
import com.smart.device.access.service.impl.TDeviceElectricServiceImpl;
import com.smart.device.access.service.impl.TDeviceSmokeServiceImpl;
import com.smart.device.access.service.impl.TDeviceWaterpressServiceImpl;

import java.util.HashMap;

/**
 * 基础设备工厂方法map常量值
 *
 * @author ms
 * @since 2020-05-27
 */
public class DeviceMapConstant {
    public static final HashMap<Integer, Class> DEVICE_MAP = new HashMap<>();

    static {
        /******************************header*********************************/
        //烟感设备
        DEVICE_MAP.put(11, TDeviceSmokeServiceImpl.class);
        //水压 压力设备
        DEVICE_MAP.put(13, TDeviceWaterpressServiceImpl.class);
        //液位设备
        DEVICE_MAP.put(15, TDeviceWaterpressServiceImpl.class);
        //燃气设备
        DEVICE_MAP.put(16, TDeviceSmokeServiceImpl.class);
        //电力设备
        DEVICE_MAP.put(17, TDeviceElectricServiceImpl.class);
        // 摄像头
        DEVICE_MAP.put(20, TDeviceCamerasServiceImpl.class);


    }

}
