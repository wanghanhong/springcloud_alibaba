package com.smart.message.manage.strategy.service;

import cn.hutool.http.HttpStatus;
import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.sms.service.impl.TemplateSms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * USER: gll
 * DATE: 2020/4/27
 * TIME: 13:56
 * Describe:短信接口
 */
@Slf4j
@Service
public class SmsStrategyImp {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
    @Resource
    private RedisTemplate redisTemplate;
    volatile Map<String,String> exist_ = new HashMap<>();

    public synchronized FebsResponse yunSmsSend(YunBaseModel yunBaseModel) {
        TemplateSms sms = new TemplateSms();
        Map<String, String> result = new HashMap<>();

        if(yunBaseModel.getSmsParam() != null){
            String[] arr = yunBaseModel.getSmsParam().split(";");
            String[] phonearr = yunBaseModel.getPhone().split(",");
            for (int i=0;i<phonearr.length;i++){
                String phone = phonearr[i];
                String phonepx = "sms"+phone+yunBaseModel.getDeviceCode();
                try {
//                     这里存在多线程的问题。
//                      A:第一次运行之后会在 exist_ 和redis 里面存放标记
//                     B:如果 exist_ 中电话设备号 不存在的话，发短信
//                     C: 如果 exist_ 中电话设备号存在，证明发了一次短信了， 从redis 里面判断是否失效了，
//                     失效了是可以再发的。
                    String exist = exist_.get(phonepx);
                    if (exist !=null && "1".equals(exist)){
                        String exist_redis = (String)redisTemplate.opsForValue().get(phonepx);
                        log.info("短信redis--主键--"+phonepx+"-exist是否标记成功-"+exist+"-"+sdf.format(new Date()));
                        if (exist_redis  !=null && "1".equals(exist_redis)){
                        }else{
                            exist_.put(phonepx,"1");
                            result = sms.sendSms(phone, arr[0], arr[1],arr[2]);
                            //将 标记 放入到redis中，一分钟 发一次短信
                            redisTemplate.opsForValue().set(phonepx,"1",90, TimeUnit.SECONDS);
                            log.info("发过短信后--"+result);
                        }
                    }else{
                        exist_.put(phonepx,"1");
                        result = sms.sendSms(phone, arr[0], arr[1],arr[2]);
                        redisTemplate.opsForValue().set(phonepx,"1",90, TimeUnit.SECONDS);
                        log.info("短信赋值--"+result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    exist_.put(phonepx,"0");
                    redisTemplate.opsForValue().set(phonepx,"0",90, TimeUnit.SECONDS);
                    log.info("短信失败redis--"+phonepx);
                }
            }
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }



}
