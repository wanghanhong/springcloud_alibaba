package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockLogMapper extends BaseMapper<TLivestockLog> {

    IPage<TLivestockLog> tLivestockLogList(Page<TLivestockLog> page, @Param("vo")TLivestockLog vo);


}
