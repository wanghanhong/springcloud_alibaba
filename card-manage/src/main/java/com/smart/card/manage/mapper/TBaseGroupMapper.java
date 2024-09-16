package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TBaseGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TBaseGroupMapper extends BaseMapper<TBaseGroup> {

    IPage<TBaseGroup> tBaseGroupList(Page<TBaseGroup> page, @Param("vo")TBaseGroup vo);


}
