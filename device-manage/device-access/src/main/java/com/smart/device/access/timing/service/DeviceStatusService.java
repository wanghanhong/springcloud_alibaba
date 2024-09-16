package com.smart.device.access.timing.service;

import com.smart.device.access.feign.DeviceAccessInstallFeign;
import com.smart.device.access.feign.DeviceAccessMessageFeign;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.mapper.TDeviceElectricMapper;
import com.smart.device.access.mapper.TDeviceSmokeMapper;
import com.smart.device.access.mapper.TDeviceWaterpressMapper;
import com.smart.device.access.service.ITDeviceElectricService;
import com.smart.device.access.service.ITDeviceSmokeService;
import com.smart.device.access.service.ITDeviceWaterpressService;
import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.access.entity.vo.DeviceRedisVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.constant.RedisConst;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DeviceStatusService {

    @Resource
    private DeviceAccessMessageFeign deviceAccessMessageFeign;
    @Resource
    private DeviceAccessInstallFeign deviceAccessInstallFeign;
    @Resource
    private ITDeviceSmokeService deviceSmokeService;
    @Resource
    private ITDeviceWaterpressService deviceWaterpressService;
    @Resource
    private ITDeviceElectricService deviceElectricService;
    @Resource
    private TDeviceDictMapper deviceDictMapper;
    @Resource
    private TDeviceSmokeMapper deviceSmokeMapper;
    @Resource
    private TDeviceWaterpressMapper deviceWaterpressMapper;
    @Resource
    private TDeviceElectricMapper deviceElectricMapper;
    @Resource
    private RedisTemplate redisTemplate;

    public void DeviceStatusHighHeart(){
        DeviceStatusMain(1);
    }
    public void DeviceStatusLowHeart(){
        DeviceStatusMain(2);
    }

    private void DeviceStatusMain(Integer type){
        List<TDeviceDict> list = deviceDictMapper.queryDeviceTypeALL();
        Map<Integer,Integer> dictMap = list.stream().collect(Collectors.toMap(TDeviceDict::getDeviceType,TDeviceDict::getHeartInterval,(key1, key2)->key2));

        /**
         *  DeviceBaseVo 有最近的一次设备心跳时间
         * 查询最新的时间，比对dictMap 设备的心跳间隔，如果在最近的3个周期内之外，判定离线
          */
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            setSmokeState(dictMap, dateTime,type);
            setWaterpresssState(dictMap, dateTime,type);
            setElectricState(dictMap, dateTime,type);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setSmokeState(Map<Integer, Integer> dictMap, LocalDateTime dateTime,Integer type) {
        List<DeviceBaseVo> devices = deviceSmokeService.selectSmokeAll(type);
        devices.stream().forEach(e->{
            try {
                // 查询最近一次心跳
                SdDeviceVo heartVo = deviceAccessMessageFeign.selectSmokeLast(e.getDeviceId());
                Integer heartInterval = dictMap.get(e.getDeviceType());
                // 获取设备离线-法定的时间
                LocalDateTime standtime = dateTime.minusMinutes(heartInterval * 3);

                TDeviceSmoke vo = new TDeviceSmoke();
                TManagerSmoke manager = new TManagerSmoke();
                vo.setId(e.getDeviceId());
                manager.setDeviceId(e.getDeviceId());

                if (heartVo != null && heartVo.getCreateTime() != null && heartVo.getCreateTime().isAfter(standtime)) {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                } else {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                }
                deviceSmokeMapper.updateById(vo);
                deviceAccessInstallFeign.updateSmokeStatus(manager);
            }catch (Exception e1){
                e1.printStackTrace();
            }

        });
    }

    private void setWaterpresssState(Map<Integer, Integer> dictMap, LocalDateTime dateTime,Integer type) {
        List<DeviceBaseVo> devices = deviceWaterpressService.selectWaterpressAll(type);
        devices.stream().forEach(e->{
            try {
                // 查询最近一次心跳
                SdDeviceVo heartVo = deviceAccessMessageFeign.selectWaterpressLast(e.getDeviceId());
                Integer heartInterval = dictMap.get(e.getDeviceType());
                // 获取设备离线-法定的时间
                LocalDateTime standtime = dateTime.minusMinutes(heartInterval * 3);

                TDeviceWaterpress vo = new TDeviceWaterpress();
                TManagerWaterpress manager = new TManagerWaterpress();
                vo.setId(e.getDeviceId());
                manager.setDeviceId(e.getDeviceId());
                if (heartVo != null && heartVo.getCreateTime() != null && heartVo.getCreateTime().isAfter(standtime)) {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                } else {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                }
                deviceWaterpressMapper.updateById(vo);
                deviceAccessInstallFeign.updateWaterpressStatus(manager);
            }catch (Exception e1){
                e1.printStackTrace();
            }

        });
    }

    private void setElectricState(Map<Integer, Integer> dictMap, LocalDateTime dateTime,Integer type) {
        List<DeviceBaseVo> devices = deviceElectricService.selectElectricAll(type);
        devices.stream().forEach(e->{
            try {
                // 查询最近一次心跳
                SdDeviceVo heartVo = deviceAccessMessageFeign.selectElectricLast(e.getDeviceId());
                Integer heartInterval = dictMap.get(e.getDeviceType());
                // 获取设备离线-法定的时间
                LocalDateTime standtime = dateTime.minusMinutes(heartInterval * 3);

                TDeviceElectric vo = new TDeviceElectric();
                TManagerElectric manager = new TManagerElectric();
                vo.setId(e.getDeviceId());
                manager.setDeviceId(e.getDeviceId());
                if (heartVo != null && heartVo.getCreateTime() != null && heartVo.getCreateTime().isAfter(standtime)) {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                } else {
                    vo.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                    manager.setDeviceState(DeviceConstant.DEVICE_STATE_OFFLINE);
                    manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_OFFLINE_NAME);
                }
                deviceElectricMapper.updateById(vo);
                deviceAccessInstallFeign.updateElectricStatus(manager);
            }catch (Exception e1){
                e1.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);


        LocalDateTime standtime = dateTime.minusMinutes(1440);


        System.out.println(standtime);

        if(dateTime.isAfter(standtime)){
            System.out.println(111);
        }
    }

    // 查询所有的设备（异常的），然后放入到redis 里面。从 接口里面来了一个数据，
    // 比对下redis ，redis是离线的，更改数据库正常，和redis 正常。
    public void onLineDevice() {
        try {
            List<DeviceRedisVo> list = deviceSmokeMapper.selectOffLineList();
            for(DeviceRedisVo vo:list){
                String key = RedisConst.CACHE_DEVICE_IMEI+vo.getImei();
                redisTemplate.opsForValue().set(key,vo,1,TimeUnit.HOURS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
