package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDeviceExt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDeviceExtMapper extends BaseMapper<TDeviceExt> {

    IPage<TDeviceExt> tDeviceExtList(Page<TDeviceExt> page, @Param("vo")TDeviceExt vo);


}
