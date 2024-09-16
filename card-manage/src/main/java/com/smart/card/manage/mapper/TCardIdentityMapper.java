package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardIdentity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardIdentityMapper extends BaseMapper<TCardIdentity> {

    IPage<TCardIdentity> tCardIdentityList(Page<TCardIdentity> page, @Param("vo")TCardIdentity vo);


}
