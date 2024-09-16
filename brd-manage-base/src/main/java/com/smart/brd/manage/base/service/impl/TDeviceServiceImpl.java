package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.TDevice;
import com.smart.brd.manage.base.mapper.TDeviceMapper;
import com.smart.brd.manage.base.service.ITDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.service.Video;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 */
@Service
public class TDeviceServiceImpl extends ServiceImpl<TDeviceMapper, TDevice> implements ITDeviceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDeviceMapper tDeviceMapper;
    @Resource
    private Video video;

    @Override
    public IPage<TDevice> tDeviceList(PageDomain page, TDevice vo) {
        Page<TDevice> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tDeviceMapper.tDeviceList(pg,vo);
    }

    @Override
    public TDevice tDeviceAdd(TDevice entity) {
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
        TDevice tDevice = tDeviceMapper.selectById(id);
        tDevice.setDeleteFlag(1);
        return tDeviceMapper.updateById(tDevice);
    }

    @Override
    public TDevice tDeviceUpdate(TDevice entity) {
        tDeviceMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDevice tDeviceDetail(TDevice entity) {
        return tDeviceMapper.selectById(entity.getDeviceId());
    }

    @Override
    public TDevice tdeviceDetailUrl(TDevice entity) {
        TDevice detail = tDeviceMapper.selectById(entity.getDeviceId());
        String urlVlc = video.getUrlVlc(detail.getDeviceCode());
        detail.setUrlVlc(urlVlc);
        return detail;
    }
    @Override
    public List<TDevice> queryDeviceList(TDevice entity) {
        LambdaQueryWrapper<TDevice> wrapper = new LambdaQueryWrapper<>();
        if (entity.getDeviceType() == null){
            wrapper.like(TDevice::getDeviceType, BrdConstant.DEVICE_TYPE_CAMERAS);
        }else{
            wrapper.like(TDevice::getDeviceType,entity.getDeviceType());
        }
        if (entity.getDeviceName()!=null){
            wrapper.like(TDevice::getDeviceName,entity.getDeviceName());
        }
        if (!StringUtils.isEmpty(entity.getDeptIds())){
            String[] split = entity.getDeptIds().split(",");
            wrapper.in(TDevice::getDeptId, split);
        }
        wrapper.eq(TDevice::getDeleteFlag,0);
        return tDeviceMapper.selectList(wrapper);
    }
    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TDevice> findList(Long[] ids) {
        QueryWrapper<TDevice> wrapper=new QueryWrapper();
        if(Objects.nonNull(ids)){
            wrapper.in("device_id",ids);
        }else {
            return tDeviceMapper.selectList(null);
        }
        return tDeviceMapper.selectList(wrapper);
    }

}
