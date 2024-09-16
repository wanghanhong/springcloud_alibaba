package com.smart.card.common.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDict;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
import com.smart.card.common.dict.service.ITBaseDictService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 */
@Service
public class TBaseDictServiceImpl extends ServiceImpl<TBaseDictMapper, TBaseDict> implements ITBaseDictService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public IPage<TBaseDict> tBaseDictList(PageDomain page, TBaseDict vo) {
        Page<TBaseDict> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseDict> iPage = tBaseDictMapper.tBaseDictList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseDict tBaseDictAdd(TBaseDict entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            this.save(entity);
        }else{
            tBaseDictUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tBaseDictDel(Long id) {
        int res = tBaseDictMapper.deleteById(id);
        return res;
    }

    @Override
    public TBaseDict tBaseDictUpdate(TBaseDict entity) {
        tBaseDictMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseDict tBaseDictDetail(TBaseDict entity) {
        TBaseDict detail = tBaseDictMapper.selectById(entity.getId());
        return detail;
    }
    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<DictDto> tByDict(String dictTypeId) {
        if(!dictTypeId.startsWith("d")){
            dictTypeId = "dict_type_"+dictTypeId;
        }
        List<DictDto> list = new ArrayList<>();
        try {
            list = (List<DictDto>)redisTemplate.opsForValue().get(dictTypeId);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null || list.isEmpty()){
            list =tBaseDictMapper.tBaseDictListNopage(dictTypeId);
        }
        return list;
    }




}
