package com.smart.device.install.service.inspection;


import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspectionDetails;
import com.smart.device.install.entity.vo.HisInspectionVo;

import java.util.List;

/**
 * @author f
 */
public interface ITHisInspectionDetailsService extends IService<THisInspectionDetails> {

    // 巡检记录-第二步保存。
    THisInspectionDetails hisInspectionDetailsAdd(HisInspectionVo entity);
    // 巡检记录-详情。
    HisInspectionVo getInsDetailsGroup(Long inspectionId);

    THisInspectionDetails InspectionAdd(THisInspectionDetails entity);



    void UpdateById(THisInspectionDetails tHisInspectionDetails);

    THisInspectionDetails selectId(Long fireId);



}