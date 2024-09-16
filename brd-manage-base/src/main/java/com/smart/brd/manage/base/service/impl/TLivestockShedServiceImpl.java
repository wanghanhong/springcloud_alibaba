package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.entity.TLivestockShed;
import com.smart.brd.manage.base.mapper.TLivestockShedMapper;
import com.smart.brd.manage.base.service.ITLivestockShedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;

import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author
 */
@Service
public class TLivestockShedServiceImpl extends ServiceImpl<TLivestockShedMapper, TLivestockShed> implements ITLivestockShedService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockShedMapper tLivestockShedMapper;

    @Override
    public IPage<TLivestockShed> tLivestockShedList(PageDomain page, TLivestockShed vo) {
        Page<TLivestockShed> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tLivestockShedMapper.tLivestockShedList(pg,vo);
    }

    @Override
    public TLivestockShed tLivestockShedAdd(TLivestockShed entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getShedId() == null ){
            entity.setShedId(id);
            entity.setFieldId(entity.getDeptId());
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tLivestockShedUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tLivestockShedDel(Long id) {
        return tLivestockShedMapper.deleteById(id);
    }

    @Override
    public TLivestockShed tLivestockShedUpdate(TLivestockShed entity) {
        tLivestockShedMapper.updateById(entity);
        return entity;
    }

    @Override
    public TLivestockShed tLivestockShedDetail(TLivestockShed entity) {
        return tLivestockShedMapper.selectById(entity.getShedId());
    }

    /**------通用方法开始结束-----------------------------------------*/


    @Override
    public List<TLivestockShed> queryShedList(TLivestockShed entity) {
        LambdaQueryWrapper<TLivestockShed> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(entity.getDeptIds())) {
            String[] split = entity.getDeptIds().split(",");
            wrapper.in(TLivestockShed::getDeptId, split);
        }
        wrapper.eq(TLivestockShed::getDeleteFlag, 0);
        wrapper.orderByAsc(TLivestockShed::getShedName);
        return tLivestockShedMapper.selectList(wrapper);
    }

    @Override
    public TLivestockShed quetyShed(Long fieldId,String shedName){
        QueryWrapper<TLivestockShed> shedWrapper = new QueryWrapper<>();
        shedWrapper.eq("field_id", fieldId);
        shedWrapper.eq("shed_name", shedName);
        shedWrapper.eq("delete_flag",0);
        return tLivestockShedMapper.selectOne(shedWrapper);
    }
}
