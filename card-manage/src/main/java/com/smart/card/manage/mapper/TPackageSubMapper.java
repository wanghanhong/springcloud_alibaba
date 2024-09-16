package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPackageSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPackageSubMapper extends BaseMapper<TPackageSub> {

    IPage<TPackageSub> tPackageSubList(Page<TPackageSub> page, @Param("vo")TPackageSub vo);


}
