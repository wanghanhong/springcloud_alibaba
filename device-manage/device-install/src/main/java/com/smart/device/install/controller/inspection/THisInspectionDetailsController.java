package com.smart.device.install.controller.inspection;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspectionDetails;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.install.entity.vo.DeviceVo;
import com.smart.device.install.entity.vo.DownloadData;
import com.smart.device.install.entity.vo.HisInspectionVo;
import com.smart.device.install.service.ITBaseFirehydrantService;
import com.smart.device.install.service.ITManagerElectricService;
import com.smart.device.install.service.ITManagerSmokeService;
import com.smart.device.install.service.ITManagerWaterpressService;
import com.smart.device.install.service.inspection.ITHisInspectionDetailsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author q
 */
@RestController
@RequestMapping("/api/v2/install/his/InspectionDetails")
public class THisInspectionDetailsController {
    @Resource
    private ITHisInspectionDetailsService itHisInspectionDetailsService;

    @Resource
    private ITBaseFirehydrantService iTBaseFirehydrantService;
    @Resource
    private ITManagerSmokeService itManagerSmokeService;
    @Resource
    private ITManagerElectricService itManagerElectricService;
    @Resource
    private ITManagerWaterpressService itManagerWaterpressService;

    TBaseFirehydrant entity = new TBaseFirehydrant();
    TManagerElectric tManagerElectric = new TManagerElectric();
    TManagerSmoke tManagerSmoke = new TManagerSmoke();
    TManagerWaterpress tManagerWaterpress = new TManagerWaterpress();

    @ApiOperation(value = "巡检记录详情添加接口-第二步")
    @ApiImplicitParam
    @PostMapping("/hisInspectionDetailAdd")
    public Result hisInspectionAdd(@RequestBody HisInspectionVo entity) {
        try {
            itHisInspectionDetailsService.hisInspectionDetailsAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }
    @ApiOperation(value = "巡检记录详情-获取待修、待检等详情")
    @ApiImplicitParam
    @GetMapping("/getInsDetailsGroup")
    public Result getInsDetailsGroup(Long inspectionId) {
        try {
            HisInspectionVo vo = itHisInspectionDetailsService.getInsDetailsGroup(inspectionId);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }

    @ApiOperation(value = "报告下载接口")
    @ApiImplicitParam
    @GetMapping(value = "/uploadExcel")
    public void uploadExcel(HttpServletResponse response, Long inspectionId) {

        List<DownloadData> downloadDatas = new ArrayList<>();
        DownloadData downloadData = new DownloadData();
        downloadData.setBatch(11L);
        downloadData.setCompanyName("中国电信");
        downloadDatas.add(0, downloadData);

        ExcelWriter writer = null;
        OutputStream out = null;
        try {

            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            String fileName = "巡检报告单";
            Sheet sheet = new Sheet(1, 0, DownloadData.class);
            sheet.setSheetName("用户信息");
            writer.write(downloadDatas, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.finish();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




















    @ApiOperation(value = "巡检消防设备详情添加接口")
    @ApiImplicitParam
    @PostMapping("/InspectionAdd")
    public Result InspectionAdd(@RequestBody THisInspectionDetails tHisInspectionDetails) {
        try {

            tHisInspectionDetails.setCreateTime(LocalDateTime.now());

            if (itHisInspectionDetailsService.selectId(tHisInspectionDetails.getFireId()) == null) {

                itHisInspectionDetailsService.InspectionAdd(tHisInspectionDetails);
                entity.setUpdateTime(LocalDateTime.now());
                entity.setDeviceStatus(tHisInspectionDetails.getDeviceStatus());
                iTBaseFirehydrantService.baseFirehydrantUpdate(entity);
            } else {

                entity.setDeviceStatus(tHisInspectionDetails.getDeviceStatus());
                itHisInspectionDetailsService.UpdateById(tHisInspectionDetails);
                entity.setUpdateTime(LocalDateTime.now());
                iTBaseFirehydrantService.baseFirehydrantUpdate(entity);

              /*  tHisInspectionDetails.setDeviceId(tHisInspectionDevice.getDeviceId());
                tHisInspectionDetails.setDeviceStatus(tHisInspectionDevice.getDeviceStatus());
                itHisInspectionDetailsService.UpdateById( tHisInspectionDetails);*/
            }

            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }





    @ApiOperation(value = "巡检设备记录详情更新接口")
    @ApiImplicitParam
    @PostMapping("/hisInspectionUpdate")
    public Result hisInspectionUpdate(@RequestBody DeviceVo deviceVo) {
        try {

            if (deviceVo.getDeviceType() == 11 || deviceVo.getDeviceType() == 16) {
                tManagerSmoke.setDeviceState(deviceVo.getDeviceState());
                tManagerSmoke.setQuestionContent(deviceVo.getQuestionContent());
                itManagerSmokeService.managerSmokeUpdate(tManagerSmoke);

            }

            if (deviceVo.getDeviceType() == 13 || deviceVo.getDeviceType() == 15) {
                tManagerWaterpress.setDeviceState(deviceVo.getDeviceState());
                tManagerWaterpress.setQuestionContent(deviceVo.getQuestionContent());
                itManagerWaterpressService.managerWaterpressUpdate(tManagerWaterpress);
            }


            if (deviceVo.getDeviceType() == 17) {
                tManagerElectric.setDeviceState(deviceVo.getDeviceState());
                tManagerElectric.setQuestionContent(deviceVo.getQuestionContent());
                itManagerElectricService.managerElectricUpdate(tManagerElectric);
            }

            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }





}
