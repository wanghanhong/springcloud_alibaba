package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TFeedAdditive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TFeedAdditiveMapper extends BaseMapper<TFeedAdditive> {

    IPage<TFeedAdditive> tFeedAdditiveList(Page<TFeedAdditive> page, @Param("vo")TFeedAdditive vo);


}
