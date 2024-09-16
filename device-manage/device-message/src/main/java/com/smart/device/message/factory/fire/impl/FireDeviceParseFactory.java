package com.smart.device.message.factory.fire.impl;

import com.smart.common.utils.spring.SpringUtils;
import com.smart.device.message.factory.fire.DeviceParseFactory;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.factory.fire.constant.FactoryConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 消防设备解析工厂
 *
 * @author 三多
 * @Time 2020/5/9
 */
@Service
public class FireDeviceParseFactory implements DeviceParseFactory {

    /**
     * @param type 类型
     * @param data 需要解析的数据
     * @return
     */
    @Override
    public String analysis(String type, String data) {
        //解析设备
        FireDeviceParse deviceSms = factoryMethod(type);
        return Objects.nonNull(deviceSms) ? deviceSms.parse(data) : "";

    }

    /**
     * 工厂方法
     * 根据类型获取对应的解析方式
     *
     * @param
     * @return
     */
    protected FireDeviceParse factoryMethod(String type) {
        Class clazz = FactoryConstant.TYPE_MAP.get(type);
        FireDeviceParse fireDeviceParse = null;
        if(clazz != null){
            Object bean = SpringUtils.getBean(clazz);
            fireDeviceParse =  ((FireDeviceParse) bean);
        }
        return fireDeviceParse;
    }
}
