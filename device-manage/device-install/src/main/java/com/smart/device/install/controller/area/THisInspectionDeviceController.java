package com.smart.device.install.controller.area;


import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.common.install.entity.THisInspectionDevice;
import com.smart.device.install.mapper.inspection.THisInspectionDeviceMapper;
import com.smart.device.install.service.inspection.ITHisInspectionDetailsService;
import com.smart.device.install.service.inspection.ITHisInspectionDeviceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rq
 */
@RestController
@RequestMapping("/api/v2/install/his/InspectionDevice")
public class THisInspectionDeviceController {
@Resource
   private ITHisInspectionDeviceService itHisInspectionDeviceService;
  @Resource
   private THisInspectionDeviceMapper deviceMapper;

  @Resource
   private ITHisInspectionDetailsService itHisInspectionDetailsService;
    @ApiOperation(value = "巡检记录接口")
    @ApiImplicitParam
    @GetMapping("/hisInspectionn")
    public Result HisInspection(Long inspectionId) {
        try {

            List list=new ArrayList();

            List list1=new ArrayList();

            List list2=new ArrayList();
            List list3=new ArrayList();
            List list4=new ArrayList();

                      List<THisInspectionDevice>  repairList  =   itHisInspectionDeviceService.hisInspectionRepairList(inspectionId);
            List<THisInspectionDevice>  replaceList =  itHisInspectionDeviceService.hisInspectionReplaceList(inspectionId);
            List<THisInspectionDevice> loseList  =itHisInspectionDeviceService.hisInspectionLoseList(inspectionId);
            THisInspection tHisInspection=itHisInspectionDeviceService.selectById(inspectionId);
            if(null!= repairList && repairList.size()!= 0){
              list2.addAll(repairList) ;

            }
            if(null!= replaceList && replaceList.size()!= 0){
                list3.addAll(replaceList) ;

            }
            if(null!= loseList && loseList.size()!=0){
                list4.addAll(loseList) ;

            }


            list1.add(list2);
             list1.add(list3);
            list1.add(list4);

            if (tHisInspection == null) {
                tHisInspection = new THisInspection();


            }
            list.add(0,tHisInspection);

            list.addAll(list1);
             return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }





   /* @ApiOperation(value = "巡检设备记录详情添加接口")
    @ApiImplicitParam
    @PostMapping("/hisInspectionDeviceAdd")
    public Result hisInspectionAdd(@RequestBody  THisInspectionDevice tHisInspectionDevice) {
        try {
            THisInspectionDetails tHisInspectionDetails= new THisInspectionDetails();
            tHisInspectionDevice.setCreateTime(LocalDateTime.now());

            if (deviceMapper.selectId(tHisInspectionDevice.getId())==null){

                deviceMapper.insert(tHisInspectionDevice);

        }else{

            deviceMapper.UpdateByIds(tHisInspectionDevice);



             tHisInspectionDetails.setDeviceStatus(tHisInspectionDevice.getDeviceStatus());
            itHisInspectionDetailsService.UpdateById( tHisInspectionDetails);
            }

            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }

*/

   /* @ApiOperation(value = "巡检设备记录详情添加接口")
    @ApiImplicitParam
    @PostMapping("/hisInspectionUpdate")
    public Result hisInspectionUpdate(@RequestBody  THisInspectionDevice tHisInspectionDevice) {
        try {
*//*
                IdWorker idWorker = new IdWorker(0, 0);
                //生成id
            tHisInspectionDevice.setId(idWorker.nextId());*//*
            tHisInspectionDevice.setCreateTime(LocalDateTime.now());

            *//* tHisInspectionDevice.setCreateTime(LocalDateTime.now());*//*
            deviceMapper.updateById(tHisInspectionDevice);

            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }

*/



}
