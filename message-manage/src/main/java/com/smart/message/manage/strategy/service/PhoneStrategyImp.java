package com.smart.message.manage.strategy.service;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.yunhu.service.impl.YunHuService;
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
 * TIME: 14:23
 * Describe:呼叫接口策略
 *
 * @author l
 */
@Slf4j
@Service
public class PhoneStrategyImp{

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
    @Resource
    private YunHuService yunHuService;
    @Resource
    private RedisTemplate redisTemplate;

    public FebsResponse yunSmsSend(YunBaseModel yunBaseModel) {
        // 重试3次
        Map<String,Object> map = alarmPhoneAgain(yunBaseModel);
        Boolean flag = Boolean.valueOf(String.valueOf(map.get("flag")));
        if(flag == false ){
            map = alarmPhoneAgain(yunBaseModel);
            flag = Boolean.valueOf(String.valueOf(map.get("flag")));
        }
        if(flag == false ){
            map = alarmPhoneAgain(yunBaseModel);
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    public Map<String,Object> alarmPhoneAgain(YunBaseModel yunBaseModel) {
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        if(yunBaseModel.getSmsParam() != null){
            String[] arr = yunBaseModel.getPhone().split(",");
            for (int i=0;i<arr.length;i++){
                String phone = arr[i];
                String phonepx = "phone"+phone+yunBaseModel.getDeviceCode();
                // 先打电话-在redis 里面保存当前的设备的拨打记录。
                try {
                    //将 电话标记 放入到redis中，一分钟打一次电话
                    String exist = (String)redisTemplate.opsForValue().get(phonepx);
                    log.info("发电话redis--主键--"+phonepx+"-exist是否标记成功-"+exist+"-"+sdf.format(new Date()));
                    if (exist  !=null && "1".equals(exist)){
                    }else{
                        JSONObject obj = yunHuService.voiceNotify(yunBaseModel.getPhone(), yunBaseModel.getPhoneParam());
                        map.put("obj",obj);
                        redisTemplate.opsForValue().set(phonepx,"1",90, TimeUnit.SECONDS);
                        log.info("电话赋值--"+obj);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    flag = false;
                    redisTemplate.opsForValue().set(phonepx,"0",90, TimeUnit.SECONDS);
                    log.info("电话失败redis--"+phonepx);
                }
            }
        }
        map.put("flag",flag);
        return map;
    }

    public static void main(String[] args) {

    }


}
