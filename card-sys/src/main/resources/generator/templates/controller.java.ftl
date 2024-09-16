package ${package.Controller};

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Service}.${table.serviceName};
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @author ${author}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/api/card/v1/${entity?lower_case}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ${table.serviceName} ${table.serviceName?substring(1)?uncap_first};

    @ApiOperation(value = "查询接口")
    @GetMapping("/${entity?uncap_first}List")
    public Result ${entity?uncap_first}List(HttpServletRequest request,PageDomain page,${entity} entity) {
        try {
            IPage iPage = ${table.serviceName?substring(1)?uncap_first}.${entity?uncap_first}List(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/${entity?uncap_first}Add")
    public Result ${entity?uncap_first}Add(@RequestBody ${entity} entity) {
        try {
            ${table.serviceName?substring(1)?uncap_first}.${entity?uncap_first}Add(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/${entity?uncap_first}Del")
    public Result ${entity?uncap_first}Del(Long id) {
        try {
            ${table.serviceName?substring(1)?uncap_first}.${entity?uncap_first}Del(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/${entity?uncap_first}Update")
    public Result ${entity?uncap_first}Update(@RequestBody ${entity} entity) {
        try {
            ${table.serviceName?substring(1)?uncap_first}.${entity?uncap_first}Update(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/${entity?uncap_first}Detail")
    public Result ${entity?uncap_first}Detail(HttpServletRequest request,${entity} entity) {
        try {
            ${entity} vo = ${table.serviceName?substring(1)?uncap_first}.${entity?uncap_first}Detail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
</#if>
