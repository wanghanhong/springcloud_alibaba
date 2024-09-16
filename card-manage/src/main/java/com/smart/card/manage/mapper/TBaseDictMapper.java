package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TBaseDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TBaseDictMapper extends BaseMapper<TBaseDict> {

    IPage<TBaseDict> tBaseDictList(Page<TBaseDict> page, @Param("vo")TBaseDict vo);


}
