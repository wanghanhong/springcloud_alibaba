package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardPackageMapper extends BaseMapper<TCardPackage> {

    IPage<TCardPackage> tCardPackageList(Page<TCardPackage> page, @Param("vo")TCardPackage vo);


}
