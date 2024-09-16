package com.smart.message.manage.monitor.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.message.manage.monitor.entity.SdDeviceVoice;

/**
 * @author cf
 * @since 2019-09-16
 */
public interface SdDeviceVoiceService  extends IService<SdDeviceVoice> {

    // 发送语音后，保存先关语音信息
    SdDeviceVoice insertDeviceVoices(JSONObject obj, SdDeviceVoice vo);

    void updateDeviceVoices(String callId, String state);

    IPage<SdDeviceVoice> deviceVoices(SdDeviceVoice vo);

    IPage<SdDeviceVoice> deviceVoicesByAlarmId(String alarmId);

}
