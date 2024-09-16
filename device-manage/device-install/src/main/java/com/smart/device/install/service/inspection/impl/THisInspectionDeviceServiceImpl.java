package com.smart.device.install.service.inspection.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.common.install.entity.THisInspectionDevice;
import com.smart.device.install.mapper.inspection.THisInspectionDeviceMapper;
import com.smart.device.install.service.inspection.ITHisInspectionDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class THisInspectionDeviceServiceImpl extends ServiceImpl<THisInspectionDeviceMapper, THisInspectionDevice> implements ITHisInspectionDeviceService {
@Resource
  private   THisInspectionDeviceMapper tHisInspectionDeviceMapper;




    @Override
    public List<THisInspectionDevice> hisInspectionRepairList(Long inspectionId) {

        List<THisInspectionDevice> tHisInspectionDeviceList=tHisInspectionDeviceMapper.hisInspectionRepairList(inspectionId);

        return tHisInspectionDeviceList;
    }

    @Override
    public THisInspection selectById(Long inspectionId) {

        THisInspection tHisInspection=tHisInspectionDeviceMapper.selectByIds(inspectionId);


        return tHisInspection;
    }

    @Override
    public List<THisInspectionDevice> hisInspectionReplaceList(Long inspectionId) {


        List<THisInspectionDevice> tHisInspectionDeviceList=tHisInspectionDeviceMapper.hisInspectionReplaceList(inspectionId);

        return tHisInspectionDeviceList;
    }



    @Override
    public List<THisInspectionDevice> hisInspectionLoseList(Long inspectionId) {
        List<THisInspectionDevice> tHisInspectionDeviceList=tHisInspectionDeviceMapper.hisInspectionLoseList(inspectionId);

        return tHisInspectionDeviceList;
    }
    }

