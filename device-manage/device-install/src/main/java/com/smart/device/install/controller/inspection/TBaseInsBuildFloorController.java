package com.smart.device.install.controller.inspection;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.install.entity.vo.InsBuildFloorVo;
import com.smart.device.install.service.inspection.ITBaseInsBuildFloorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@RestController
@RequestMapping("/api/v2/install/base/Insbuildfloor")
public class TBaseInsBuildFloorController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseInsBuildFloorService iTBaseInsBuildFloorService;

//    @ApiOperation(value = "巡检详情查询接口")
//    @ApiImplicitParam
//    @GetMapping("/baseInsBuildFloorList")
//    public Result baseInsBuildFloorList(PageDomain page, TBaseInsBuildFloor entity) {
//        try {
//            IPage iPage = iTBaseInsBuildFloorService.baseInsBuildFloorList(page,entity);
//            return Result.SUCCESS(new PageResult(iPage));
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
//        }
//    }
//    @ApiOperation(value = "巡检详情添加接口")
//    @ApiImplicitParam
//    @PostMapping("/baseInsBuildFloorAdd")
//    public Result baseInsBuildFloorAdd(@RequestBody TBaseInspection entity) {
//        try {
//            iTBaseInsBuildFloorService.baseInsBuildFloorAdd(entity);
//            return Result.SUCCESS();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
//        }
//    }
//
//    @ApiOperation(value = "巡检详情删除接口")
//    @ApiImplicitParam
//    @GetMapping("/baseInsBuildFloorDel")
//    public Result baseInsBuildFloorDel(Long id) {
//        try {
//            iTBaseInsBuildFloorService.baseInsBuildFloorDel(id);
//            return Result.SUCCESS();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
//        }
//    }
//
////    @ApiOperation(value = "修改巡检详情接口")
//    @ApiImplicitParam
//    @PostMapping("/baseInsBuildFloorUpdate")
//    public Result baseInsBuildFloorUpdate(@RequestBody TBaseInsBuildFloor entity) {
//        try {
//            iTBaseInsBuildFloorService.baseInsBuildFloorUpdate(entity);
//            return Result.SUCCESS();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
//        }
//    }
//
//    @ApiOperation(value = "根据ID查询巡检详情详情")
//    @ApiImplicitParam
//    @GetMapping("/selecTBaseInsBuildFloorDetail")
//    public Result selecTBaseInsBuildFloorByID(Long id) {
//        try {
//            TBaseInsBuildFloor vo = iTBaseInsBuildFloorService.selectBaseInsBuildFloorByID(id);
//            return Result.SUCCESS(vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
//        }
//    }
    /**------基本方法结束-----------------------------------------*/
    @ApiOperation(value = "根据 巡检ID、建筑物ID 和楼层 查询 当前楼层所有的绑定的设备")
    @ApiImplicitParam
    @GetMapping("/selecByInsAndBuildAndFloor")
    public Result selecByInsAndBuildAndFloor(InsBuildFloorVo vo) {
        try {
            List<InsBuildFloorVo> list = iTBaseInsBuildFloorService.selecByInsAndBuildAndFloor(vo);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

}
