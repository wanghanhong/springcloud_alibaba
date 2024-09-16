package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.vo.LiveStockVo;
import com.smart.common.utils.upload.FastDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TLivestock;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author
 */
@RestController
@Api(tags = "家畜存栏")
@RequestMapping("/api/v1/brd/tlivestock")
public class TLivestockController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockService tLivestockService;
    @Autowired
    private FastDfsUtil fastDfsUtil;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockList")
    @RequiresPermissions("livestockManagement:view")
    public Result tLivestockList(HttpServletRequest request,PageDomain page,LiveStockVo entity) {
        try {
            IPage<LiveStockVo> iPage = tLivestockService.tLivestockList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "livestockId", value = "id", paramType = "body", required = false,dataType = "Long"),
            @ApiImplicitParam(name = "typeId", value = "种类id", paramType = "body", required = false,dataType = "Long"),
            @ApiImplicitParam(name = "birthDate", value = "出生日期", paramType = "body", required = false, dataType = "Date"),
            @ApiImplicitParam(name = "fieldId", value = "养殖场id", paramType = "body", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "bedId", value = "栏id", paramType = "body", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "entryDate", value = "入栏日期", paramType = "body", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "groupTransferDate", value = "转群日期", paramType = "body", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "parentsPublic", value = "父母代公本", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentSurrogate", value = "父母代母本", paramType = "body", required = false, dataType = "String")
    })
    @PostMapping("/tLivestockAdd")
    public Result tLivestockAdd(@RequestBody LiveStockVo entity) {
        try {
            tLivestockService.tLivestockAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockDel")
    public Result tLivestockDel(Long livestockId) {
        try {
            tLivestockService.tLivestockDel(livestockId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockUpdate")
    public Result tLivestockUpdate(@RequestBody TLivestock entity) {
        try {
            tLivestockService.tLivestockUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockDetail")
    public Result tLivestockDetail(HttpServletRequest request,TLivestock entity) {
        try {
            LiveStockVo vo = tLivestockService.tLivestockDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    /**------基本方法结束-----------------------------------------*/
    @ApiOperation(value = "所有的动物耳标-列表")
    @GetMapping("/queryLivestockList")
    public Result queryLivestockList(HttpServletRequest request,LiveStockVo entity) {
        try {
            List<LiveStockVo> list = tLivestockService.queryLivestockList(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "异常的动物耳标-列表")
    @GetMapping("/queryLivestockTreat")
    public Result queryLivestockTreat(HttpServletRequest request,LiveStockVo entity) {
        try {
//            entity.setStatus("151");
            List<LiveStockVo> list = tLivestockService.queryLivestockList(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "导入接口")
    @PostMapping("/tLivestockImport")
    public Result tBrdFieldImport(HttpServletRequest request,MultipartFile path, LiveStockVo entity) {
        try {
            if(path==null){
                throw new CustomException("文件为空");
            }

            String contentType = path.getContentType();
            if(!BrdConstant.EXCEL_XLS.equals(contentType) || !".xls".equals(fastDfsUtil.getLastIndexName(path))){
                throw new CustomException("文件类型不正确");
            }
            return tLivestockService.livestockImport(path,entity);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "下载模板接口")
    @PostMapping("/tLivestockExport")
    public Result tBrdFieldExport(HttpServletResponse response) {
        try {
            tLivestockService.livestockExport(response);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "防疫的动物查询接口")
    @PostMapping("/tLivestockVaccine")
    public Result tLivestockVaccine(@RequestBody LiveStockVo entity) {
        try {
            PageDomain page=new PageDomain();
            page.setPageNum(entity.getPageNum());
            page.setPageSize(entity.getPageSize());
            IPage<LiveStockVo> iPage = tLivestockService.tLivestockList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


}
