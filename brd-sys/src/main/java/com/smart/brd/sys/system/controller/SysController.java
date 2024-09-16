package com.smart.brd.sys.system.controller;

import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.service.DeptService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.service.DeptService;
import javax.annotation.Resource;

@Slf4j
@Validated
@RestController
public class SysController extends BaseController {

    @Resource
    private DeptService deptService;

    @PostMapping("/api/v2/dept/deptForUpdate")
    public Result deptForUpdate(@RequestBody Dept dept) {
        try {
            if(dept != null && dept.getDeptId() != null ){
                Dept entity = deptService.selectById(dept.getDeptId());
                if(entity != null ){
                    deptService.updateDept(dept);
                }else{
                    deptService.createDept(dept);
                }
            }else{
                deptService.createDept(dept);
            }
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }

}
