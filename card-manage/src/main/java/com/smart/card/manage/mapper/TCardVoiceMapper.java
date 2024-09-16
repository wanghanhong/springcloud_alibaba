package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardVoice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardVoiceMapper extends BaseMapper<TCardVoice> {

    IPage<TCardVoice> tCardVoiceList(Page<TCardVoice> page, @Param("vo")TCardVoice vo);


}
