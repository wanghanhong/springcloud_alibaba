package com.smart.card.sys.system.controller;

import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.service.DeptService;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Validated
@RestController
public class WebController extends BaseController {

    @Resource
    private DeptService deptService;


    @GetMapping("/api/v1/dept/selectDept")
    public Result dictList(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        List<Dept> list = deptService.findDeptLists(request,queryRequest,dept);
        return Result.SUCCESS(list);
    }

    public static void main(String[] args) {

    }

}
