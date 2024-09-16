package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPoolPackageUse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolPackageUseMapper extends BaseMapper<TPoolPackageUse> {

    IPage<TPoolPackageUse> tPoolPackageUseList(Page<TPoolPackageUse> page, @Param("vo")TPoolPackageUse vo);

    TPoolPackageUse getUseByDate(@Param("nbr") Long nbr, @Param("date")String date);
}
