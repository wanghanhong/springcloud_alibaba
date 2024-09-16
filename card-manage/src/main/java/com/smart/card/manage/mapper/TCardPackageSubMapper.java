package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardPackageSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardPackageSubMapper extends BaseMapper<TCardPackageSub> {

    IPage<TCardPackageSub> tCardPackageSubList(Page<TCardPackageSub> page, @Param("vo")TCardPackageSub vo);


}
