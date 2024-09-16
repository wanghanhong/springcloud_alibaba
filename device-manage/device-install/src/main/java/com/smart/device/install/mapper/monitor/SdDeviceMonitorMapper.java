package com.smart.device.install.mapper.monitor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface SdDeviceMonitorMapper extends BaseMapper<DeviceMonitorVo> {

    IPage<DeviceMonitorVo> selectCompanys(Page<DeviceMonitorVo> page, @Param("vo") DeviceMonitorVo vo);

    Integer queryBuildNumByCompanyId(@Param("vo") DeviceMonitorVo vo);
    Integer queryBuildingSonDeviceNum(@Param("vo") DeviceMonitorVo vo);
    Integer queryDealNum(@Param("vo") DeviceMonitorVo vo);

    IPage<DeviceMonitorVo> deviceAlarmsList(Page<DeviceMonitorVo> page, @Param("vo") DeviceMonitorVo alarmVO);


}
