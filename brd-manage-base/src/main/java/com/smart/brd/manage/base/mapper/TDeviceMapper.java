package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.TDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 
 */
public interface TDeviceMapper extends BaseMapper<TDevice> {

    IPage<TDevice> tDeviceList(Page<TDevice> page, @Param("vo")TDevice vo);

}
