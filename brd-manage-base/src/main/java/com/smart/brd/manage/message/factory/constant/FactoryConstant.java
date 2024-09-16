package com.smart.brd.manage.message.factory.constant;

import com.smart.brd.manage.message.factory.analysis.impl.DeviceParseImpl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author junglelocal
 */
public class FactoryConstant {

    public static ConcurrentHashMap<String,Class> TYPE_MAP = new ConcurrentHashMap<>();

    static {
        TYPE_MAP.put("11", DeviceParseImpl.class);
    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

}
