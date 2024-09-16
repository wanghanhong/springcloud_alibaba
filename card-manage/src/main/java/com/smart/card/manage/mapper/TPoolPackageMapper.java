package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPoolPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolPackageMapper extends BaseMapper<TPoolPackage> {

    IPage<TPoolPackage> tPoolPackageList(Page<TPoolPackage> page, @Param("vo")TPoolPackage vo);


}
