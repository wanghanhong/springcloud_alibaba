package com.smart.device.message.data.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.message.data.service.TAlarmSmokeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 */
@RestController
@RequestMapping("/api/v2/alarm/smoke")
public class TAlarmSmokeController extends BaseController {

    @Resource
    private TAlarmSmokeService alarmSmokeService;


}
