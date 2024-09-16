package com.smart.device.access.service;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.access.entity.vo.CtWingVo;
import com.smart.device.access.entity.vo.SdDeviceCtWingVO;

public interface CtWingAPIService {

    CtWingVo QueryCtwingParam(Integer type) throws Exception;

    //  根据设备id 查询设备信息
    JSONObject createDevice(SdDeviceCtWingVO vo) throws Exception;

    //  删除 设备信息
    JSONObject deleteDevice(String deviceIds, Integer type) throws Exception;

    //  根据设备deviceId 更新设备信息
    JSONObject updateDevice(SdDeviceCtWingVO vo) throws Exception;

    //  根据设备id 查询设备信息
    JSONObject queryDevice(String deviceId, Integer type) throws Exception;

    //  批量查询设备信息
    JSONObject queryDeviceList(SdDeviceCtWingVO vo) throws Exception;


    // 终端单个最新状态查询接口
    JSONObject queryDeviceStatus(String deviceId, Integer type) throws Exception;

    JSONObject queryEventList(String deviceId, Integer type) throws Exception;


    JSONObject GetSubscription(String deviceId) throws Exception;


}
