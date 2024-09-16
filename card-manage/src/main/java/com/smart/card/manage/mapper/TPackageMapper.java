package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPackageMapper extends BaseMapper<TPackage> {

    IPage<TPackage> tPackageList(Page<TPackage> page, @Param("vo")TPackage vo);


}
