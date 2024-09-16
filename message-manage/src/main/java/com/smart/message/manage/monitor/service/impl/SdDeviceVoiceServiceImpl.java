package com.smart.message.manage.monitor.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.message.manage.common.domain.QueryRequest;
import com.smart.message.manage.monitor.dao.SdDeviceVoiceMapper;
import com.smart.message.manage.monitor.entity.SdDeviceVoice;
import com.smart.message.manage.monitor.service.SdDeviceVoiceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * yuyin 服务实现类
 * </p>
 *
 * @author l
 * @since 2019-09-16
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class SdDeviceVoiceServiceImpl extends ServiceImpl<SdDeviceVoiceMapper, SdDeviceVoice> implements SdDeviceVoiceService {

    @Resource
    private SdDeviceVoiceMapper sdDeviceVoiceMapper;

    public SdDeviceVoice insert(SdDeviceVoice vo) {
        IdWorker idWorker=new IdWorker(0,0);

        vo.setId(idWorker.nextId());
//            vo.setDeviceId(deviceId);
        vo.setType(0);
        vo.setState(1);// 起始值为成功
//            vo.setContactMan(phones);
        vo.setSendTime(new Date());
        vo.setAddTime(new Date());

        sdDeviceVoiceMapper.insert(vo);
        return vo ;
    }

    @Override
    public SdDeviceVoice insertDeviceVoices(JSONObject obj, SdDeviceVoice vo) {
        IdWorker idWorker=new IdWorker(0,0);
        JSONObject resp = obj.getJSONObject("resp");

        String respCode = String.valueOf(resp.get("respCode"));
        String callId = "";
        if("0".equals(respCode)){
            try {
                JSONObject voiceNotify = resp.getJSONObject("voiceNotify");
                // 呼叫状态：0-等待中；1-通话失败
                callId = String.valueOf(voiceNotify.get("callId"));

                vo.setCallId(callId);
                SdDeviceVoice res =insert(vo);
                return res ;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new SdDeviceVoice();
    }

    @Override
    public void updateDeviceVoices(String callId,String state) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("callId",callId);
        List<SdDeviceVoice> list =  sdDeviceVoiceMapper.selectByMap(columnMap);
        if(list != null && list.size() > 0){
            SdDeviceVoice voice = list.get(0);
            voice.setState(Integer.parseInt(state));
            voice.setFeedbackTime(new Date());
            voice.setFeedbackResult("电话已接通。");
            sdDeviceVoiceMapper.updateById(voice);
        }

    }

    @Override
    public IPage<SdDeviceVoice> deviceVoices(SdDeviceVoice vo) {
            QueryRequest queryRequest = new QueryRequest();
        try {
            LambdaQueryWrapper<SdDeviceVoice> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(vo.getDeviceId() != null,SdDeviceVoice::getDeviceId, vo.getDeviceId());

            Page<SdDeviceVoice> page = new Page<>();
//            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取语音失败", e);
            return new Page<>();
        }
    }

    @Override
    public IPage<SdDeviceVoice> deviceVoicesByAlarmId(String alarmId) {
        try {
            LambdaQueryWrapper<SdDeviceVoice> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(StringUtils.isNotBlank(alarmId),SdDeviceVoice::getDeviceId, alarmId);

            Page<SdDeviceVoice> page = new Page<>();
            QueryRequest queryRequest = new  QueryRequest();
//            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取语音失败", e);
            return new Page<>();
        }
    }



}
