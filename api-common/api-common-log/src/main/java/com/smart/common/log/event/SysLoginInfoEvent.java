package com.smart.common.log.event;


import com.smart.system.common.entity.SysLoginInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @description: 系统日志事件
 * @author: SanDuo
 * @date: 2020/5/23 16:19
 * @version: 1.0
 */
public class SysLoginInfoEvent extends ApplicationEvent {
    /***/
    private static final long serialVersionUID = -9084676463718966036L;

    public SysLoginInfoEvent(SysLoginInfo source) {
        super(source);
    }
}