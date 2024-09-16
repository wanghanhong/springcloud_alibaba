package com.smart.device.install.service.inspection;


import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.common.install.entity.THisInspectionDevice;

import java.util.List;

/**
 * @author f
 */
public interface ITHisInspectionDeviceService extends IService<THisInspectionDevice> {


    List<THisInspectionDevice> hisInspectionRepairList( Long inspectionId);

    THisInspection selectById( Long inspectionId);


    List<THisInspectionDevice> hisInspectionReplaceList(Long inspectionId);


    List<THisInspectionDevice> hisInspectionLoseList( Long inspectionId);










}
