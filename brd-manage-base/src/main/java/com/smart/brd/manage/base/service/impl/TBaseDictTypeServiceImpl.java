package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TBaseDictType;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TBaseDictTypeMapper;
import com.smart.brd.manage.base.service.ITBaseDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 */
@Service
public class TBaseDictTypeServiceImpl extends ServiceImpl<TBaseDictTypeMapper, TBaseDictType> implements ITBaseDictTypeService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseDictTypeMapper tBaseDictTypeMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TBaseDictType> tBaseDictTypeList(PageDomain page, TBaseDictType vo) {
        Page<TBaseDictType> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tBaseDictTypeMapper.tBaseDictTypeList(pg,vo);
    }

    @Override
    @Transactional
    public TBaseDictType tBaseDictTypeAdd(TBaseDictType entity) {
        int max=0;
        int maxDictType = tBaseDictTypeMapper.findMax();
        int maxDict = tBaseDictMapper.findMax();
        if (maxDictType>=maxDict){
            max=maxDictType;
        }
        if(maxDictType<maxDict) {
            max=maxDict;
        }
        max = max + 1;
        String dy="dict_type_" + max;
        entity.setDeleteFlag(0);
        entity.setUniqueId(max);
        entity.setDictTypeId(dy);
        tBaseDictTypeMapper.insert(entity);
        List<TBaseDict> dictList = entity.getDictList();
        for (TBaseDict s : dictList) {
                max++;
                TBaseDict tBaseDict = new TBaseDict();
                tBaseDict.setDictId(max);
                tBaseDict.setDictName(s.getDictName());
                tBaseDict.setDictTypeId(dy);
                tBaseDict.setCreateTime(LocalDateTime.now());
                tBaseDict.setDeleteFlag(0);
                tBaseDict.setSortno(max);
                tBaseDictMapper.insert(tBaseDict);
        }
        return entity;
    }

    @Override
    public int tBaseDictTypeDel(Long id) {
        TBaseDictType tBaseDictType = tBaseDictTypeMapper.selectById(id);
        tBaseDictType.setDeleteFlag(1);
        return tBaseDictTypeMapper.updateById(tBaseDictType);
    }

    @Override
    public TBaseDictType tBaseDictTypeUpdate(TBaseDictType entity) {
        int max=0;
        int maxDictType = tBaseDictTypeMapper.findMax();
        int maxDict = tBaseDictMapper.findMax();
        if (maxDictType>=maxDict){
            max=maxDictType;
        }
        if(maxDictType<maxDict) {
            max=maxDict;
        }
        TBaseDictType tBaseDictType = tBaseDictTypeMapper.selectById(entity.getId());
        LambdaQueryWrapper<TBaseDict> eq = new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictTypeId, tBaseDictType.getDictTypeId());
        List<TBaseDict> tBaseDicts = tBaseDictMapper.selectList(eq);
        List<Long> idss=new ArrayList<>();
        for (TBaseDict tBaseDict : tBaseDicts) {
            idss.add(tBaseDict.getId());
        }
        List<TBaseDict> dictList = entity.getDictList();
        List<Long> ids=new ArrayList<>();
        for (TBaseDict s : dictList) {
            if(Objects.isNull(s.getId())){
                max++;
                TBaseDict tBaseDict = new TBaseDict();
                tBaseDict.setDictId(max);
                tBaseDict.setDictName(s.getDictName());
                tBaseDict.setDictTypeId(tBaseDictType.getDictTypeId());
                tBaseDict.setCreateTime(LocalDateTime.now());
                tBaseDict.setDeleteFlag(0);
                tBaseDict.setSortno(max);
                tBaseDictMapper.insert(tBaseDict);
            }else {
                ids.add(s.getId());
                tBaseDictMapper.updateById(s);
            }
        }
        if(idss.size()>ids.size()){
            idss.removeAll(ids);
            tBaseDictMapper.deleteBatchIds(idss);
        }
        tBaseDictTypeMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseDictType tBaseDictTypeDetail(TBaseDictType entity) {
        TBaseDictType detail = tBaseDictTypeMapper.selectById(entity.getId());
        QueryWrapper<TBaseDict> wrapper=new QueryWrapper<>();
        wrapper.eq("dict_type_id",detail.getDictTypeId());
        List<TBaseDict> tBaseDicts = tBaseDictMapper.selectList(wrapper);
        if (!tBaseDicts.isEmpty()){
            List<TBaseDict> list=new ArrayList<>();
            for (TBaseDict tBaseDict : tBaseDicts) {
                list.add(tBaseDict);
            }
            detail.setDictList(list);
        }
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TBaseDictType> findAll() {
        QueryWrapper<TBaseDictType> wrapper = new QueryWrapper<>();
        return tBaseDictTypeMapper.selectList(wrapper);
    }

}
