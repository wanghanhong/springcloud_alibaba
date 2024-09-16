package com.smart.message.manage.yunhu.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author gll
 */
public interface YunHuServiceInter {

    JSONObject getAccounts();

    /**
     * 上传文本接口
     *
     * @param text
     * @return
     */

    JSONObject uploadText(String text);

    /**
     * 电话状态回调接口
     *
     * @param param
     * @return
     */
    String voicenotifyBack(String param);

    /**
     * 自动电话接口
     *
     * @param phones
     * @param content
     * @return
     */
//    JSONObject voiceNotify(String phones, String content);

}
