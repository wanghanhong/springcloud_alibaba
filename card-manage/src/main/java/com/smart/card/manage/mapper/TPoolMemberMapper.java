package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPoolMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolMemberMapper extends BaseMapper<TPoolMember> {

    IPage<TPoolMember> tPoolMemberList(Page<TPoolMember> page, @Param("vo")TPoolMember vo);

    TPoolMember getMemberByAcc(@Param("subs") Long acc);
}
