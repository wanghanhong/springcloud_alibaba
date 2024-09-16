package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TDeviceCameras;
import com.smart.brd.manage.base.mapper.TDeviceCamerasMapper;
import com.smart.brd.manage.base.service.ITDeviceCamerasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;

/**
 * @author 
 */
@Service
public class TDeviceCamerasServiceImpl extends ServiceImpl<TDeviceCamerasMapper, TDeviceCameras> implements ITDeviceCamerasService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDeviceCamerasMapper tDeviceCamerasMapper;

    @Override
    public IPage<TDeviceCameras> tDeviceCamerasList(PageDomain page, TDeviceCameras vo) {
        Page<TDeviceCameras> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tDeviceCamerasMapper.tDeviceCamerasList(pg,vo);
    }

    @Override
    public TDeviceCameras tDeviceCamerasAdd(TDeviceCameras entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tDeviceCamerasUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tDeviceCamerasDel(Long id) {
        return tDeviceCamerasMapper.deleteById(id);
    }

    @Override
    public TDeviceCameras tDeviceCamerasUpdate(TDeviceCameras entity) {
        tDeviceCamerasMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDeviceCameras tDeviceCamerasDetail(TDeviceCameras entity) {
        return tDeviceCamerasMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
