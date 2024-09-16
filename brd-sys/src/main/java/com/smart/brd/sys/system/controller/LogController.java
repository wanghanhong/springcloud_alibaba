package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smart.brd.sys.common.annotation.Log;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.exception.FebsException;
import com.smart.brd.sys.system.domain.po.SysLog;
import com.smart.brd.sys.system.service.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("log")
public class LogController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private LogService logService;

    @GetMapping
//    @RequiresPermissions("log:view")
    public FebsResponse logList(QueryRequest request, SysLog sysLog) {
        Map<String, Object> result = getDataTable(logService.findLogs(request, sysLog));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @Log("删除系统日志")
    @DeleteMapping("/{ids}")
//    @RequiresPermissions("log:delete")
    public FebsResponse deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) throws FebsException {
        try {
            String[] logIds = ids.split(StringPool.COMMA);
            this.logService.deleteLogs(logIds);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除日志失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PostMapping("excel")
//    @RequiresPermissions("log:export")
    public FebsResponse export(QueryRequest request, SysLog sysLog, HttpServletResponse response) throws FebsException {
        try {
            List<SysLog> sysLogs = this.logService.findLogs(request, sysLog).getRecords();
            ExcelKit.$Export(SysLog.class, response).downXlsx(sysLogs, false);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }
}
