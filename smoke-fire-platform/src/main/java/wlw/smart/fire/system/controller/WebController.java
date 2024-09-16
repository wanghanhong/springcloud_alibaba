package wlw.smart.fire.system.controller;

import cn.hutool.http.HttpStatus;
import wlw.smart.fire.common.domain.FebsResponse;
import wlw.smart.fire.common.domain.QueryRequest;
import wlw.smart.fire.system.domain.po.Dept;
import wlw.smart.fire.system.service.DeptService;
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
