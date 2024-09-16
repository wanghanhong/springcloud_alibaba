package com.smart.device.install.service.inspection.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseFirefighter;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.install.mapper.inspection.TBaseInspectionMapper;
import com.smart.device.install.service.inspection.ITBaseFirefighterService;
import com.smart.device.install.service.inspection.ITBaseInsBuildFloorService;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import com.smart.device.install.service.inspection.ITBaseInspectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author f
 */
@Service
public class TBaseInspectionServiceImpl extends ServiceImpl<TBaseInspectionMapper, TBaseInspection> implements ITBaseInspectionService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseInspectionMapper TBaseInspectionMapper;
    @Resource
    private ITBaseFirefighterService itBaseFirefighterService;

    @Override
    public IPage<TBaseInspection> baseInspectionList(PageDomain page, TBaseInspection vo) {
        Page<TBaseInspection> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseInspection> iPage = TBaseInspectionMapper.baseInspectionList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseInspection baseInspectionAdd(TBaseInspection entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long parentId = idWorker.nextId();
        if(entity.getId() == null ){
            //生成id
            entity.setId(idWorker.nextId());
            entity.setCreateTime(LocalDateTime.now());
            entity.setStatus(0);
            entity.setType(0);
            entity.setSortNo(2);
            this.save(entity);
        }
        return entity;
    }

    @Override
    public int baseInspectionDel(Long id) {
        return TBaseInspectionMapper.deleteById(id);
    }

    @Override
    public TBaseInspection baseInspectionUpdateAndFirefighter(TBaseInspection entity) {

        //  没有ID的话保存下。
        TBaseFirefighter vo = new TBaseFirefighter();
        vo.setPhone(entity.getPhone());
        vo = itBaseFirefighterService.selectBaseFirefighter(vo);

        entity.setFirefighterId(vo.getId());
        entity.setFirefighterName(vo.getName());
        entity.setPhone(vo.getPhone());
        entity.setUpdateTime(LocalDateTime.now());

        TBaseInspectionMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseInspection baseInspectionUpdat(TBaseInspection entity) {
        entity.setUpdateTime(LocalDateTime.now());
        TBaseInspectionMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseInspection selectBaseInspectionByID(Long id) {
        TBaseInspection entity = TBaseInspectionMapper.selectById(id);
        return entity;
    }
    /**------通用方法开始结束-----------------------------------------*/
    @Override
    public List<TBaseInspection> insAndInsBuildsList(TBaseInspection vo){
        List<TBaseInspection> list = TBaseInspectionMapper.insAndInsBuildsList(vo);
        return list;
    }



}
