package com.smart.device.access.service;

import com.smart.common.core.domain.Result;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;

import java.util.List;

/**
 * 基础设备管理工厂方法
 *
 * @author ms
 * @since 2020-05-27
 */
public interface BaseDeviceFactory {
    Result saveDeviceInfo(List<ImportDeviceVO> successList);

    Result selectDeviceInfo(DeviceAccessVO deviceVO);

    Result createCtwingDevice(DeviceAccessVO deviceVO);

    void devicesDelete(Long id);
}
