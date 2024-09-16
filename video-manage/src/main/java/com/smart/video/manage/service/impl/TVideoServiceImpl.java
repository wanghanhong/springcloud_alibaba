package com.smart.video.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.video.manage.service.ITVideoService;
import com.smart.video.manage.entity.TVideo;
import com.smart.video.manage.mapper.TVideoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 */
@Service
public class TVideoServiceImpl extends ServiceImpl<TVideoMapper, TVideo> implements ITVideoService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TVideoMapper tDeviceMapper;

    @Override
    public IPage<TVideo> tDeviceList(PageDomain page, TVideo vo) {
        Page<TVideo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TVideo> iPage = tDeviceMapper.tDeviceList(pg,vo);
        return iPage;
    }

    @Override
    public TVideo tDeviceAdd(TVideo entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getDeviceId() == null ){
            entity.setDeviceId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tDeviceUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tDeviceDel(Long id) {
        TVideo tDevice = tDeviceMapper.selectById(id);
        tDevice.setDeleteFlag(1);
        int res = tDeviceMapper.updateById(tDevice);
        return res;
    }

    @Override
    public TVideo tDeviceUpdate(TVideo entity) {
        tDeviceMapper.updateById(entity);
        return entity;
    }

    @Override
    public TVideo tDeviceDetail(TVideo entity) {
        TVideo detail = tDeviceMapper.selectById(entity.getDeviceId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TVideo> findList(Long[] ids) {
        QueryWrapper<TVideo> wrapper=new QueryWrapper();
        if(Objects.nonNull(ids)){
            wrapper.in("device_id",ids);
        }else {
            List<TVideo> tDevices = tDeviceMapper.selectList(null);
            return tDevices;
        }
        List<TVideo> tDevices = tDeviceMapper.selectList(wrapper);
        return tDevices;
    }

}
