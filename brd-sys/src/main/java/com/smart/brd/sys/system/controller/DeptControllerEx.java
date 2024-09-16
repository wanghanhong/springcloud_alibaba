package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.brd.sys.common.annotation.Log;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.exception.FebsException;
import com.smart.brd.sys.system.dao.DeptMapper;
import com.smart.brd.sys.system.dao.DeptMapperEx;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.DeptEx;
import com.smart.brd.sys.system.service.DeptService;
import com.smart.brd.sys.system.service.DeptServiceEx;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("dept")
public class DeptControllerEx extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private DeptServiceEx deptServiceEx;
    @Resource
    private DeptMapperEx deptMapperEx;

    @GetMapping
//    @RequiresPermissions("dept:view")
    public FebsResponse deptList(HttpServletRequest request, QueryRequest queryRequest, DeptEx dept) {
        Map<String, Object> depts = this.deptServiceEx.findDepts(request,queryRequest,dept);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(depts);
    }

    @Log("新增部门")
    @PostMapping
//    @RequiresPermissions("dept:add")
    public FebsResponse addDept(@Valid @RequestBody DeptEx dept) throws FebsException {
        try {
            //查找上级目录中没有相同名称的公司
            Long is = deptMapperEx.deptNameQuery(dept.getDeptName(),dept.getParentId());
            if(is != 0 ){
                return new FebsResponse().code(211).message("名称不能重复");
            }else{
                this.deptServiceEx.createDept(dept);
                return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
            }
        } catch (Exception e) {
            message = "新增部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("删除部门")
    @DeleteMapping("/{deptIds}")
//    @RequiresPermissions("dept:delete")
    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws FebsException {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptServiceEx.deleteDepts(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("修改部门")
    @PutMapping
//    @RequiresPermissions("dept:update")
    public FebsResponse updateDept(@Valid @RequestBody DeptEx dept) throws FebsException {
        try {
            this.deptServiceEx.updateDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PostMapping("excel")
    public FebsResponse export(HttpServletRequest request, DeptEx dept, QueryRequest queryRequest, HttpServletResponse response) throws FebsException {
        try {
            List<DeptEx> depts = this.deptServiceEx.findDeptLists(request,queryRequest,dept);
            ExcelKit.$Export(DeptEx.class, response).downXlsx(depts, false);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

}
