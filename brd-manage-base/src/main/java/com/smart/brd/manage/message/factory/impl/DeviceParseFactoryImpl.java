package com.smart.brd.manage.message.factory.impl;

import com.smart.brd.manage.message.factory.DeviceParseFactory;
import com.smart.brd.manage.message.factory.analysis.DeviceParse;
import com.smart.brd.manage.message.factory.constant.FactoryConstant;
import com.smart.common.utils.spring.SpringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author junglelocal
 */
@Service
public class DeviceParseFactoryImpl implements DeviceParseFactory {

    @Override
    public String analysis(String type, String data) {
        //解析设备
        DeviceParse deviceSms = factoryMethod(type);
        return Objects.nonNull(deviceSms) ? deviceSms.parse(data) : "";
    }

    @Override
    public String analysisNew(String type, String data) {
        //解析设备
        DeviceParse deviceSms = factoryMethod(type);
        return Objects.nonNull(deviceSms) ? deviceSms.parseNew(data) : "";
    }

    /**
     * 工厂方法
     * 根据类型获取对应的解析方式
     *
     * @param type type
     * @return parse
     */
    protected DeviceParse factoryMethod(String type) {
        Class clazz = FactoryConstant.TYPE_MAP.get(type);
        DeviceParse deviceParse = null;
        if(clazz != null){
            Object bean = SpringUtils.getBean(clazz);
            deviceParse =  ((DeviceParse) bean);
        }
        return deviceParse;
    }
}
