package com.smart.brd.manage.base.screen.controller;

import com.smart.brd.manage.base.common.task.LivestockTask;
import com.smart.brd.manage.base.screen.entity.ScreenEntity;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@RestController
@RequestMapping("/api/v1/brd/analysis")
public class Test6Controller {

    @Resource
    private LivestockTask livestockTask;

    @GetMapping("/test")
    public Result test(HttpServletRequest request, ScreenEntity entity) {
        try {
            livestockTask.saveShedentry();
            return Result.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


}
