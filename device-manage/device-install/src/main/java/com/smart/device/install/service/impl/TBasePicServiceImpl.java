package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBasePic;
import com.smart.device.install.mapper.TBasePicMapper;
import com.smart.device.install.service.ITBasePicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author f
 */
@Service
public class TBasePicServiceImpl extends ServiceImpl<TBasePicMapper, TBasePic> implements ITBasePicService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBasePicMapper TBasePicMapper;
    
    @Override
    public IPage<TBasePic> basePicList(PageDomain page, TBasePic vo) {
        Page<TBasePic> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBasePic> iPage = TBasePicMapper.basePicList(pg,vo);
        return iPage;
    }

    @Override
    public TBasePic basePicAdd(TBasePic entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        TBasePicMapper.insert(entity);
        return entity;
    }

    @Override
    public int basePicDel(Long id) {
        return TBasePicMapper.deleteById(id);
    }

    @Override
    public TBasePic basePicUpdate(TBasePic entity) {
        entity.setUpdateTime(LocalDateTime.now());
        TBasePicMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBasePic selectBasePicByID(Long id) {
        TBasePic entity = TBasePicMapper.selectById(id);
        return entity;
    }
    /**------通用方法开始结束-----------------------------------------*/

}
