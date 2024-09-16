package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPoolPackageMemberUse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolPackageMemberUseMapper extends BaseMapper<TPoolPackageMemberUse> {

    IPage<TPoolPackageMemberUse> tPoolPackageMemberUseList(Page<TPoolPackageMemberUse> page, @Param("vo")TPoolPackageMemberUse vo);


}
