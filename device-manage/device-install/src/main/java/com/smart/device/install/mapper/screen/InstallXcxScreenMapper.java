package com.smart.device.install.mapper.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface InstallXcxScreenMapper extends BaseMapper<DeviceCompanyVo> {

    // 查询设备的关联信息
    List<DeviceCompanyVo> queryInstallDevicesList(@Param("vo") DeviceCompanyVo vo, @Param("start") int start, @Param("limit") int limit);
    Long queryInstallDevicesListCount(@Param("vo") DeviceCompanyVo vo);




}
