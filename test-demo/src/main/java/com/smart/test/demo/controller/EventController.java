package com.smart.test.demo.controller;

import com.smart.common.core.domain.Result;
import com.smart.test.demo.annotation.LogRecord;
import com.smart.test.demo.enmus.OperatorType;
import com.smart.test.demo.event.BaseEventPublish;
import com.smart.test.demo.event.entity.LogEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 三多
 * @Time 2020/6/12
 */
@Api("事件")
@RestController
@RequestMapping("event")
public class EventController {

    /**
     * 手动发布异步
     *
     * @param logInfo
     * @return
     */
    @ApiOperation("test")
    @PostMapping("/log/record")
    public Result testEvent(LogEntity logInfo) {
        BaseEventPublish.recordLog("1", "127.0.0.1", "登录");
        return Result.SUCCESS();
    }

    /**
     * 注解发布
     *
     * @param logInfo
     * @return
     */
    @ApiOperation("anno")
    @LogRecord(id = "12", ip = "192.168.1.21", remark = OperatorType.SAVE)
    @PostMapping("/log/anno/record")
    public Result testAnnoEvent(LogEntity logInfo) {
        BaseEventPublish.recordLog(logInfo);
        return Result.SUCCESS();
    }


}
