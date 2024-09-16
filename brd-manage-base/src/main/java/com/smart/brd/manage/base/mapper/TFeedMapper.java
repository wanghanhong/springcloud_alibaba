package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TFeed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TFeedMapper extends BaseMapper<TFeed> {

    IPage<TFeed> tFeedList(Page<TFeed> page, @Param("vo")TFeed vo);


}
