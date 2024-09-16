package com.smart.device.install.service.inspection.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.bean.BeanUtils;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.install.common.constant.InstallConstant;
import com.smart.device.install.entity.vo.HisInspectionVo;
import com.smart.device.install.mapper.inspection.TBaseInspectionMapper;
import com.smart.device.install.mapper.inspection.THisInspectionMapper;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import com.smart.device.install.service.inspection.ITBaseInspectionService;
import com.smart.device.install.service.inspection.ITHisInspectionService;
import net.sf.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author f
 */
@Service
public class THisInspectionServiceImpl extends ServiceImpl<THisInspectionMapper, THisInspection> implements ITHisInspectionService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseInspectionMapper tBaseInspectionMapper;

    @Override
    public int hisInspectionAddOne(HisInspectionVo entity) {
        TBaseInspection ins = new TBaseInspection();
        BeanUtils.copyBeanProp(ins,entity);

        ins.setCurBuildingId(entity.getBuildingId());
        ins.setId(entity.getInspectionId());
        ins.setType(1);
        ins.setInsTime(LocalDateTime.now());
        if(entity.getSortNo() == null){
            ins.setSortNo(2);
        }
        tBaseInspectionMapper.updateById(ins);
        return 0;
    }

    @Override
    public IPage<TBaseInspection> hisInspectionList(PageDomain page, TBaseInspection vo) {
        Page<TBaseInspection> pg = new Page<>(page.getPageNum(), page.getPageSize());
        vo.setStatus(InstallConstant.HIS_INS_STATUS);
        IPage<TBaseInspection> iPage = tBaseInspectionMapper.baseInspectionList(pg,vo);
        return iPage;
    }





}
