package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TBaseDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author
 */

public interface TBaseDictTypeMapper extends BaseMapper<TBaseDictType> {

    IPage<TBaseDictType> tBaseDictTypeList(Page<TBaseDictType> page, @Param("vo")TBaseDictType vo);

    int findMax();

    Integer selectByType(@Param("dictTypeId") String dictTypeId);
}
