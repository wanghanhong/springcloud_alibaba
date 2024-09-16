package wlw.smart.fire.system.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wlw.smart.fire.common.annotation.Log;
import wlw.smart.fire.common.controller.BaseController;
import wlw.smart.fire.common.domain.FebsResponse;
import wlw.smart.fire.common.domain.QueryRequest;
import wlw.smart.fire.common.exception.FebsException;
import wlw.smart.fire.system.domain.po.Dict;
import wlw.smart.fire.system.service.DictService;

import javax.annotation.Resource;
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
@RequestMapping("dict")
public class DictController extends BaseController {

    private String message;

    @Resource
    private DictService dictService;

    @GetMapping
    //@RequiresPermissions("dict:view")
    public FebsResponse dictList(QueryRequest request, Dict dict) {
        Map<String, Object> dataTable = getDataTable(this.dictService.findDicts(request, dict));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    @Log("新增字典")
    @PostMapping
    // @RequiresPermissions("dict:add")
    public FebsResponse addDict(@Valid @RequestBody Dict dict) throws FebsException {
        try {
            this.dictService.createDict(dict);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增字典成功";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除字典")
    @DeleteMapping("/{dictIds}")
    // @RequiresPermissions("dict:delete")
    public FebsResponse deleteDicts(@NotBlank(message = "{required}") @PathVariable String dictIds) throws FebsException {
        try {
            String[] ids = dictIds.split(StringPool.COMMA);
            this.dictService.deleteDicts(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除字典成功";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改字典")
    @PutMapping
    //@RequiresPermissions("dict:update")
    public FebsResponse updateDict(@Valid @RequestBody Dict dict) throws FebsException {
        try {
            this.dictService.updateDict(dict);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改字典成功";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    //@RequiresPermissions("dict:export")
    public void export(QueryRequest request, Dict dict, HttpServletResponse response) throws FebsException {
        try {
            List<Dict> dicts = this.dictService.findDicts(request, dict).getRecords();
            ExcelKit.$Export(Dict.class, response).downXlsx(dicts, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
