package com.smart.card.sys.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.annotation.Log;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.dao.DeptMapper;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.service.DeptService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/dept")
public class DeptController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private DeptService deptService;
    @Resource
    private DeptMapper deptMapper;

    @GetMapping("/list")
    public Result deptList(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        Map<String, Object> depts = this.deptService.findDepts(request,queryRequest,dept);
        return Result.SUCCESS(depts);
    }

    @Log("新增部门")
    @PostMapping("/add")
//    @RequiresPermissions("dept:add")
    public Result addDept(@Valid @RequestBody Dept dept) {
        try {
            Long is = deptMapper.deptNameQuery(dept.getDeptName());
            if(is != null ){
                return Result.ERROR(ResultCode.FAIL,"名称不能重复");
            }else{
                this.deptService.createDept(dept);
                return Result.SUCCESS();
            }
        } catch (Exception e) {
            message = "新增部门失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("删除部门")
    @GetMapping("/del/{deptIds}")
//    @RequiresPermissions("dept:delete")
    public Result deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptService.deleteDepts(ids);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "删除部门失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("修改部门")
    @PostMapping("/update")
//    @RequiresPermissions("dept:update")
    public Result updateDept(@Valid @RequestBody Dept dept) {
        try {
            this.deptService.updateDept(dept);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改部门失败";
            log.error(message, e);
            return Result.ERROR(ResultCode.FAIL,message);
        }
    }

    @PostMapping("/excel")
    public Result export(HttpServletRequest request, Dept dept, QueryRequest queryRequest, HttpServletResponse response) {
        try {
            List<Dept> depts = this.deptService.findDeptLists(request,queryRequest,dept);
            ExcelKit.$Export(Dept.class, response).downXlsx(depts, false);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

}
