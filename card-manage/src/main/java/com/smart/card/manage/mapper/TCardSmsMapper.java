package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardSms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardSmsMapper extends BaseMapper<TCardSms> {

    IPage<TCardSms> tCardSmsList(Page<TCardSms> page, @Param("vo")TCardSms vo);


}
