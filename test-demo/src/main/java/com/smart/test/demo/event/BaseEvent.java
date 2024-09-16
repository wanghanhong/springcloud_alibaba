package com.smart.test.demo.event;

import com.smart.test.demo.event.entity.BaseSource;
import org.springframework.context.ApplicationEvent;

/**
 * 基础事件
 *
 * @author 三多
 * @Time 2020/6/12
 */
public class BaseEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    /**
     * 定义事件
     *
     * @param source 事件发生地，不能为空
     */
    public BaseEvent(BaseSource source) {
        super(source);
    }
}
