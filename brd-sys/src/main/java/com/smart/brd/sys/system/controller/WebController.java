package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.service.DeptService;
import com.smart.common.core.controller.BaseController;
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
    public FebsResponse dictList(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        List<Dept> list = deptService.findDeptLists(request,queryRequest,dept);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(list);
    }

    public static void main(String[] args) {

    }

}
