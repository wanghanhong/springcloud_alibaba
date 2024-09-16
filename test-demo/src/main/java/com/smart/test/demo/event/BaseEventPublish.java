package com.smart.test.demo.event;

import com.smart.common.utils.spring.SpringContextHolder;
import com.smart.test.demo.event.entity.LogEntity;

/**
 * 事件发布
 * @author 三多
 * @Time 2020/6/12
 */
public class BaseEventPublish {
    public static void  recordLog(String id,String ip,String remark){
        LogEntity source = new LogEntity();
        source.setId(id);
        source.setIp(ip);
        source.setRemark(remark);
        SpringContextHolder.publishEvent(new BaseEvent(source));

    }
    public static void  recordLog(LogEntity source){
        SpringContextHolder.publishEvent(new BaseEvent(source));

    }
}
