package com.smart.test.demo.event;

import com.smart.test.demo.event.entity.LogEntity;
import com.smart.test.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 * @author 三多
 * @Time 2020/6/12
 */
@Slf4j
@Component
public class BaseEventListener {

    @Autowired
    private LogService logService;

    /**
     *
     * @param event 事件
     */
    @Async
    @Order
    @EventListener(BaseEvent.class)
    public void listenerLog(BaseEvent event){
        LogEntity logEntity = (LogEntity) event.getSource();
        log.debug("插入到数据库,{},{},{}",logEntity.getId(),logEntity.getIp(), logEntity.getRemark());
        Integer integer = logService.insertLog(logEntity);
        System.out.println("打印"+logEntity.getId()+logEntity.getIp()+logEntity.getRemark());
        System.out.println(String.format("返回值= %d",integer));
    }

}
