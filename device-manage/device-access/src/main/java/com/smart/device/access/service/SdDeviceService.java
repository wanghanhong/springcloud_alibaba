package com.smart.device.access.service;

import com.smart.common.core.domain.Result;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * 设备基础信息管理服务接口
 *
 * @author ms
 * @since 2020-05-26
 */
public interface SdDeviceService {

    Result devicesImport(HttpServletRequest request, InputStream is, ImportDeviceVO deviceVO) throws Exception;

    Result selectDevicesList(DeviceAccessVO deviceVO);

    Result ctwingDeviceAdd(DeviceAccessVO deviceVO);

    void devicesDelete(DeviceAccessVO deviceVO);

}
