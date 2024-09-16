package com.smart.system.common.feign;


import com.smart.common.constant.ServiceNameConstants;
import com.smart.system.common.entity.SysLoginInfo;
import com.smart.system.common.entity.SysOperLog;
import com.smart.system.common.feign.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 日志Feign服务层
 * @author: SanDuo
 * @date: 2020/5/23 17:11
 * @version: 1.0
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    @PostMapping("operLog/save")
    public void insertOperlog(@RequestBody SysOperLog operLog);

    @PostMapping("logininfor/save")
    public void insertLoginlog(@RequestBody SysLoginInfo logininfor);
}
