package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDeviceInstallMapper extends BaseMapper<TDeviceInstall> {

    IPage<TDeviceInstall> tDeviceInstallList(Page<TDeviceInstall> page, @Param("vo")TDeviceInstall vo);


}
