package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.dto.DictDto;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TBaseDictTypeMapper;
import com.smart.brd.manage.base.service.ITBaseDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;

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

    @Resource
    private TBaseDictTypeMapper tBaseDictTypeMapper;
    /**
     *
     * 字典开头字母
     *
    * */
    private static final String DICT_START_WITH = "d";

    @Override
    public IPage<TBaseDict> tBaseDictList(PageDomain page, TBaseDict vo) {
        Page<TBaseDict> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tBaseDictMapper.tBaseDictList(pg,vo);
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
        TBaseDict tBaseDict = tBaseDictMapper.selectById(id);
        //判断该数据是否可以删除
        Integer i = tBaseDictTypeMapper.selectByType(tBaseDict.getDictTypeId());
        if (i != 0){
            throw new CustomException("该数据不能删除");
        }

        tBaseDict.setDeleteFlag(1);
        return tBaseDictMapper.updateById(tBaseDict);
    }

    @Override
    public TBaseDict tBaseDictUpdate(TBaseDict entity) {
        tBaseDictMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseDict tBaseDictDetail(TBaseDict entity) {
        return tBaseDictMapper.selectById(entity.getId());
    }
    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<DictDto> tByDict(String s) {
        if(!s.startsWith(DICT_START_WITH)){
            s = "dict_type_"+s;
        }
        List<DictDto> list = new ArrayList<>();

        //防止字典内容不更新
        list =tBaseDictMapper.tBaseDictListNopage(s);
        return list;
    }
}
