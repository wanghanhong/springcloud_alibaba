package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockShed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockShedMapper extends BaseMapper<TLivestockShed> {

    IPage<TLivestockShed> tLivestockShedList(Page<TLivestockShed> page, @Param("vo")TLivestockShed vo);


}
