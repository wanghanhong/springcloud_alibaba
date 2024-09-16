package wlw.smart.fire.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
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
import wlw.smart.fire.common.annotation.Log;
import wlw.smart.fire.common.controller.BaseController;
import wlw.smart.fire.common.domain.FebsResponse;
import wlw.smart.fire.common.domain.QueryRequest;
import wlw.smart.fire.common.exception.FebsException;
import wlw.smart.fire.system.domain.po.Dept;
import wlw.smart.fire.system.service.DeptService;
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
public class DeptController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private DeptService deptService;

    @GetMapping
    public FebsResponse deptList(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        Map<String, Object> depts = this.deptService.findDepts(request,queryRequest,dept);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(depts);
    }

    @Log("新增部门")
    @PostMapping
    // @RequiresPermissions("dept:add")
    public FebsResponse addDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            this.deptService.createDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除部门")
    @DeleteMapping("/{deptIds}")
    // @RequiresPermissions("dept:delete")
    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws FebsException {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptService.deleteDepts(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改部门")
    @PutMapping
    //@RequiresPermissions("dept:update")
    public FebsResponse updateDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            this.deptService.updateDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
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
