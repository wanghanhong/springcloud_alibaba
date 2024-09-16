package com.smart.card.sys.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.annotation.Log;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.domain.po.SysLog;
import com.smart.card.sys.system.service.LogService;
import com.smart.common.core.domain.Result;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private LogService logService;

    @GetMapping("/list")
    @RequiresPermissions("log:view")
    public Result logList(QueryRequest request, SysLog sysLog) {
        Map<String, Object> result = getDataTable(logService.findLogs(request, sysLog));
        return Result.SUCCESS(result);
    }

    @Log("删除系统日志")
    @GetMapping("/del/{ids}")
    @RequiresPermissions("log:delete")
    public Result deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        try {
            String[] logIds = ids.split(StringPool.COMMA);
            this.logService.deleteLogs(logIds);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "删除日志失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/excel")
    @RequiresPermissions("log:export")
    public Result export(QueryRequest request, SysLog sysLog, HttpServletResponse response) {
        try {
            List<SysLog> sysLogs = this.logService.findLogs(request, sysLog).getRecords();
            ExcelKit.$Export(SysLog.class, response).downXlsx(sysLogs, false);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }
}
