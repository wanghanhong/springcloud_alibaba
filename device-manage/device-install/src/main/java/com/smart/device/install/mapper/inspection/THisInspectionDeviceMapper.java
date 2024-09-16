package com.smart.device.install.mapper.inspection;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.common.install.entity.THisInspectionDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface THisInspectionDeviceMapper extends BaseMapper<THisInspectionDevice> {


    List<THisInspectionDevice> hisInspectionRepairList(@Param("inspectionId") Long inspectionId);

    THisInspection selectByIds(@Param("inspectionId") Long inspectionId);



    List<THisInspectionDevice> hisInspectionReplaceList(@Param("inspectionId")Long inspectionId);


    List<THisInspectionDevice> hisInspectionLoseList(@Param("inspectionId") Long inspectionId);

   /* void  insertOne(THisInspectionDevice tHisInspectionDevice);*/


    void UpdateByIds(@Param("tHisInspectionDevice")THisInspectionDevice tHisInspectionDevice);

    THisInspectionDevice   selectId(@Param("id")Long id);

}
