package com.smart.device.message.factory.fire.analysis.service.impl;

import com.smart.device.common.access.entity.UserInfoType;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.data.service.TAlarmSmokeService;
import com.smart.device.message.data.service.THeartSmokeService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.parse.fire.DeviceBase.DeviceBaseStandard;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.userInfo.entity.InfotransEntity;
import com.smart.device.message.parse.fire.userInfo.strategy.InfotransParseStrategy;
import com.smart.device.message.parse.fire.userInfo.strategy.InfotransStandar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/*
* 用户信息传输装置解析
* */
@Service
public class InfotransParse implements FireDeviceParse {

    @Autowired
    private InfotransParseStrategy infotransParseStrategy;
    @Autowired
    private InfotransStandar infotransStandar;
    @Resource
    private TAlarmSmokeService tAlarmSmokeService;
    @Resource
    private THeartSmokeService tHeartSmokeService;
    @Resource
    private DeviceBaseStandard deviceBaseStandard;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     *
     * {"upPacketSN":-1,"upDataSN":-1,"topic":"ad","timestamp":1609231209882,"tenantId":"10461180","serviceId":"","protocol":"tcp","productId":"15017085","payload":{"APPdata":"ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIyMjIiLAogICAgICAgICAidiIgOiAiMSIKICAgICAgfQogICBdLAogICAiYyIgOiAyNywKICAgImlkIiA6IDE2MDkyMzEyMDk3NjcsCiAgICJ0bSIgOiAxNjA5MjMxMjA5NzY3Cn0="},"messageType":"dataReport","deviceType":"","deviceId":"1501708501","assocAssetId":"","IMSI":"","IMEI":""}
     *
     *
     *
     *
     * 92设备离线 ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTAwMDkxNzAxMDM3NCIsCiAgICAgICAgICJ2IiA6ICIxIgogICAgICB9CiAgIF0sCiAgICJjIiA6IDkyLAogICAiaWQiIDogMTYwODg3Mjg2NjAwOSwKICAgInRtIiA6IDE2MDg4NzI4NjYwMDkKfQo=
     *
     *
     *
     * 27 消防设施部件状态上传 ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTAwMDkxNzAxMDM3NDAxMDAwMDAxMDEwMTAwMzkwMTI2IiwKICAgICAgICAgInYiIDogIjIiCiAgICAgIH0KICAgXSwKICAgImMiIDogMjcsCiAgICJpZCIgOiAxNjA4ODczMTQ2MDgyLAogICAidG0iIDogMTYwODg3MzE0NjA4Mgp9Cg
     *
     *
     *
     *
     *
     * 1501708501
     * 2021-04-24 22:14:23
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxOTI3MzY2MzI5MSwKICAgInRtIiA6IDE2MTkyNzM2NjMyOTEKfQo=
     * 1501708501
     * 2021-04-23 22:26:33
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxOTE4Nzk5MzU4MCwKICAgInRtIiA6IDE2MTkxODc5OTM1ODAKfQo=
     * 1501708501
     * 2021-04-23 15:07:22
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDEwMTMzIiwKICAgICAgICAgInYiIDogIjQiCiAgICAgIH0KICAgXSwKICAgImMiIDogMjcsCiAgICJpZCIgOiAxNjE5MTYxNjQyMjA0LAogICAidG0iIDogMTYxOTE2MTY0MjIwNAp9Cg==
     * 1501708501
     * 2021-04-23 13:59:59
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDEwMTMyIiwKICAgICAgICAgInYiIDogIjIiCiAgICAgIH0KICAgXSwKICAgImMiIDogMjcsCiAgICJpZCIgOiAxNjE5MTU3NTk5MjUzLAogICAidG0iIDogMTYxOTE1NzU5OTI1Mwp9Cg==
     * 1501708501
     * 2021-04-22 21:54:30
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxOTA5OTY3MDc0OSwKICAgInRtIiA6IDE2MTkwOTk2NzA3NDkKfQo=
     * 1501708501
     * 2021-04-22 16:28:58
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDEwMTM0IiwKICAgICAgICAgInYiIDogIjQiCiAgICAgIH0KICAgXSwKICAgImMiIDogMjcsCiAgICJpZCIgOiAxNjE5MDgwMTM4NTY1LAogICAidG0iIDogMTYxOTA4MDEzODU2NQp9Cg==
     * 1501708501
     * 2021-04-21 21:48:54
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxOTAxMjkzNDY5MSwKICAgInRtIiA6IDE2MTkwMTI5MzQ2OTEKfQo=
     * 1501708501
     * 2021-04-21 21:48:54
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxOTAxMjkzNDQwOCwKICAgInRtIiA6IDE2MTkwMTI5MzQ0MDgKfQo=
     * 1501708501
     * 2021-04-20 21:44:09
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMTg1IiwKICAgICAgICAgInYiIDogIjY0IgogICAgICB9CiAgIF0sCiAgICJjIiA6IDI3LAogICAiaWQiIDogMTYxODkyNjI0OTQxMCwKICAgInRtIiA6IDE2MTg5MjYyNDk0MTAKfQo=
     * 1501708501
     * 2021-04-20 07:53:42
     * ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIwMTgxMDkxNzAxMDY4MDAxMDAwMDAxMDEwMTAwMDMwMDc3IiwKICAgICAgICAgInYiIDogIjIiCiAgICAgIH0KICAgXSwKICAgImMiIDogMjcsCiAgICJpZCIgOiAxNjE4ODc2NDIyODk1LAogICAidG0iIDogMTYxODg3NjQyMjg5NQp9Cg==
     *
     * 数据解析方法
     * 1、按照码表解析数据
     * 2、将数据存储到数据库
     * 3、根据解析的数据
     * 4、定义事件
     *
     * @param data
     * @return
     */
    @Override
    public String parse(String data) {
        ParseContext<InfotransEntity> context = new ParseContext<>(infotransParseStrategy);
        InfotransEntity entity = context.parseEntity(data);
        String partid = entity.getPartId();

        //根据partId判断是烟感类型还是气感类型 还是其他
        UserInfoType dictByDeviceType = deviceBaseFeignClient.getDictByDeviceType(partid);
        Integer deviceType = dictByDeviceType.getDeviceType();
        if(DeviceConstant.device_type_smoke.equals(deviceType)){
            //烟感类型
            TAlarmSmoke alarm = infotransStandar.changeToAlarm(entity);
            THeartSmoke heart = infotransStandar.changeToHeart(entity);

            if(alarm.getImei() != null){
                String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
                String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
                if (exist  !=null && "1".equals(exist)){
                }else{
                    DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(alarm.getImei());
                    if(baseVo != null){
                        TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                        if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                            deviceBaseStandard.changeAlarmAttri(alarm,baseVo);
                            tAlarmSmokeService.smokeAdd(alarm);
                            // 报警才有策略，心跳直接跳过。
                            DeviceCompanyVo companyVo = deviceInstallFeignClient.smokePerSonByDeviceId(baseVo.getId());
                            tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                        }
                        deviceBaseStandard.changeHeartAttri(heart,baseVo);
                        tHeartSmokeService.smokeAdd(heart);
                    }
                    redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
                }

            }
        }
        return "用户信息传输装置解析完成";
    }

    public static void main(String[] args) {

            System.out.println("1这是符合的");

    }



}
