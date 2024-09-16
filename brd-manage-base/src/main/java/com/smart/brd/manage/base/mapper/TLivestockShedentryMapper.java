package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockShedentry;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockShedentryMapper extends BaseMapper<TLivestockShedentry> {

    IPage<TLivestockShedentry> tLivestockShedentryList(Page<TLivestockShedentry> page, @Param("vo") TLivestockShedentry vo);


}
