package com.smart.device.install.controller.inspection;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.TBaseInsBuild;
import com.smart.device.install.entity.vo.InspectionVo;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@RestController
@RequestMapping("/api/v2/install/base/insbuild")
public class TBaseInsBuildController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseInsBuildService itBaseInsBuildService;

    /**
     * {
     *   "inspectionId": 1,
     *   "insBuilds": [{
     *     "serialVersionUID": 1,
     *     "inspectionId": 1,
     *     "insBuildId": 53327952460382208,
     *     "buildingId": 1,
     *     "buildingName": "demoData",
     *     "pointNum": 1,
     * 	"insBuildFloors": [{
     * 		"insBuildFloorId": 53315280117432320,
     * 		"buildingId": 1,
     * 		"buildingName": "demoData",
     * 		"type": 1,
     * 		"floorNum": 1,
     * 		"escapeRouteNum": 1,
     * 		"firehydrantNum": 1,
     * 		"fireAllNum": 1,
     * 		"smokeNum": 1,
     * 		"electricNum": 1,
     * 		"waterpressNum": 1,
     * 		"picUrl": "demoData",
     * 		"content": "demoData",
     * 		"escapeRouteIs": 1,
     * 		"firehydrantIs": 1,
     * 		"fireAllIs": 1,
     * 		"deleteFlag": 1
     *   }]
     *   }]
     * }
     *
     *
     * @param entity
     * @return
     */
    @ApiOperation(value = "巡检计划修改接口-2")
    @ApiImplicitParam
    @PostMapping("/baseInsBuildUpdate")
    public Result baseInsBuildFloorAdd(@RequestBody InspectionVo entity) {
        try {
            itBaseInsBuildService.baseInsBuildUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "根据ID查询巡检详情详情-修改第二部-回显")
    @ApiImplicitParam
    @GetMapping("/selecTBaseInsBuildDetail")
    public Result selecTBaseInsBuildByID(Long inspectionId) {
        try {
            List<TBaseInsBuild> list = itBaseInsBuildService.insAndInsFloorByInsId(inspectionId);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }
    /**------基本方法结束-----------------------------------------*/


}
