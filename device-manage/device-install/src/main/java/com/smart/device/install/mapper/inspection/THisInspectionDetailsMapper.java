package com.smart.device.install.mapper.inspection;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspectionDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface THisInspectionDetailsMapper extends BaseMapper<THisInspectionDetails> {

  List<THisInspectionDetails> getHisInsDetailsList(@Param("vo") THisInspectionDetails vo);


  THisInspectionDetails selectId(@Param("fireId")Long fireId );










}
