package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.brd.sys.system.dao.DeptMapper;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smart.brd.sys.common.annotation.Log;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.exception.FebsException;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.service.DeptService;
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
//@RequestMapping("dept")
public class DeptController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private DeptService deptService;
    @Resource
    private DeptMapper deptMapper;

    //@GetMapping
    public FebsResponse deptList(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        Map<String, Object> depts = this.deptService.findDepts(request,queryRequest,dept);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(depts);
    }

    @Log("新增部门")
    //@PostMapping
//    @RequiresPermissions("dept:add")
    public FebsResponse addDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            //查找上级目录中没有相同名称的公司
            Long is = deptMapper.deptNameQuery(dept.getDeptName(),dept.getParentId());
            if(is != null ){
                return new FebsResponse().code(211).message("名称不能重复");
            }else{
                this.deptService.createDept(dept);
                return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
            }
        } catch (Exception e) {
            message = "新增部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("删除部门")
    //@DeleteMapping("/{deptIds}")
//    @RequiresPermissions("dept:delete")
    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws FebsException {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptService.deleteDepts(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("修改部门")
    //@PutMapping
//    @RequiresPermissions("dept:update")
    public FebsResponse updateDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            this.deptService.updateDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改部门失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    //@PostMapping("excel")
    public FebsResponse export(HttpServletRequest request, Dept dept, QueryRequest queryRequest, HttpServletResponse response) throws FebsException {
        try {
            List<Dept> depts = this.deptService.findDeptLists(request,queryRequest,dept);
            ExcelKit.$Export(Dept.class, response).downXlsx(depts, false);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

}
