package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPool;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolMapper extends BaseMapper<TPool> {

    IPage<TPool> tPoolList(Page<TPool> page, @Param("vo")TPool vo);


}
