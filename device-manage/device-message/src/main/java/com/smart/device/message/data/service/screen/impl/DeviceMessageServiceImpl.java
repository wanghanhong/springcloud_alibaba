package com.smart.device.message.data.service.screen.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.vo.ScreenListVo;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.device.message.data.mapper.screen.DeviceMessageScreenMapper;
import com.smart.device.message.data.mapper.screen.MWholeProvinceMapper;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.data.service.screen.DeviceMessageService;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @author f
 */
@Service
public class DeviceMessageServiceImpl extends ServiceImpl<MWholeProvinceMapper, ScreenProvinceVo> implements DeviceMessageService {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM");
    /**------通用方法开始-----------------------------------------*/
    @Resource
    private DeviceMessageScreenMapper deviceMessageScreenMapper;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DateService dateService;

    @Override
    public List<ScreenVo> deviceSmokeEventNum() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_smoke);
        List<ScreenVo> list = deviceMessageScreenMapper.deviceSmokeEventNum(vo);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceGasEventNum() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_gas);
        List<ScreenVo> list = deviceMessageScreenMapper.deviceSmokeEventNum(vo);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceWaterpressEventNum() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        List<ScreenVo> list = deviceMessageScreenMapper.deviceWaterpressEventNum(vo);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceLiquidleveEventNum() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_liquidlevel);
        List<ScreenVo> list = deviceMessageScreenMapper.deviceWaterpressEventNum(vo);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceElectricEventNum() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_electric);
        List<ScreenVo> list = deviceMessageScreenMapper.deviceElectricEventNum(vo);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByCompanySmoke() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_smoke);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByCompanySmoke(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByCompanyGas() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_gas);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByCompanySmoke(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByCompanyWaterpress() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByCompanyWaterpress(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByCompanyLiquidleve() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_liquidlevel);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByCompanyWaterpress(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByCompanyElectric() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_electric);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByCompanyElectric(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByDeviceSmoke() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_smoke);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByDeviceSmoke(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByDeviceGas() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_gas);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByDeviceSmoke(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByDeviceWaterpress() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByDeviceWaterpress(vo);
        return list;
    }

    @Override
    public List<ScreenVo> eventNumByDeviceLiquidleve() {
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_liquidlevel);
        List<ScreenVo> list = deviceMessageScreenMapper.eventNumByDeviceWaterpress(vo);
        return list;
    }

    @Override
    public ScreenListVo eventNumByDeviceElectric() {
        ScreenListVo listVo = new ScreenListVo();
        // 正常数量
        List<ScreenVo> onLine = deviceInstallFeignClient.deviceBuildCompanyElectric();
        // 故障数量
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_electric);
        vo.setStrategyType(1);
        List<ScreenVo> fault = deviceMessageScreenMapper.eventNumByDeviceElectric(vo);
        // 报警数量
        vo.setStrategyType(2);
        List<ScreenVo> alarm = deviceMessageScreenMapper.eventNumByDeviceElectric(vo);
        listVo.setOnLineList(onLine);
        listVo.setFaultList(fault);
        listVo.setAlarmList(alarm);
        return listVo;
    }

    @Override
    public Map<String,Object> eventNumByMonthSmoke() {
        ScreenVo vo = new ScreenVo();
        vo.setParentType(DeviceConstant.device_type_smoke);
        Map<String,Object> result = setSmokeList(vo);
        return result;
    }

    @Override
    public Map<String,Object> eventNumByMonthGas() {
        ScreenVo vo = new ScreenVo();
        vo.setParentType(DeviceConstant.device_type_gas);
        Map<String,Object> result = setSmokeList(vo);
        return result;
    }

    @Override
    public Map<String,Object> eventNumByMonthWaterpress() {
        Map<String,Object> result = new HashMap<String,Object>();

//        ScreenVo vo = new ScreenVo();
//        vo.setParentType(DeviceConstant.device_type_waterpress);
//        List<Map<String,Object>> maplist = setWaterpressList(vo);

        return result;
    }

    @Override
    public Map<String,Object> eventNumByMonthLiquidleve() {
        Map<String,Object> result = new HashMap<String,Object>();

//        ScreenVo vo = new ScreenVo();
//        vo.setParentType(DeviceConstant.device_type_liquidlevel);
//        List<Map<String,Object>> maplist = setWaterpressList(vo);

        return result;
    }

    @Override
    public Map<String,Object> eventNumByMonthElectric() {
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> mapList = new ArrayList<>();

        ScreenVo vo = new ScreenVo();
        vo.setParentType(DeviceConstant.device_type_electric);

        List<ScreenVo> monthList = dateService.getMonth();
        List<String> monthArray = new ArrayList<>();

        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();
        List<Integer> array3 = new ArrayList<>();
        List<Integer> array4 = new ArrayList<>();
        List<Integer> array5 = new ArrayList<>();
        List<Integer> array6 = new ArrayList<>();
        List<Integer> array7 = new ArrayList<>();

        monthList.stream().forEach(e->{
            vo.setStartTime(e.getStartTime());
            vo.setEndTime(e.getEndTime());
            List<ScreenVo> list = deviceMessageScreenMapper.eventNumByMonthElectric(vo);
            Map<String,Integer> map = list.stream().collect(Collectors.toMap(ScreenVo::getEventCode,ScreenVo::getCountNum,(key1, key2)->key2));
            array1.add(map.get("1"));
            array2.add(map.get("2"));
            array3.add(map.get("3"));
            array4.add(map.get("4"));
            array5.add(map.get("5"));
            array6.add(map.get("6"));
            array7.add(map.get("7"));
            String month =  dtf.format(e.getStartTime());
            monthArray.add(month);
        });
        Map<Integer,String> strategyMap = tStrategyAlarmService.queryStrategyMap(17);

        getAndSetList(mapList,strategyMap,array1,1);
        getAndSetList(mapList,strategyMap,array2,2);
        getAndSetList(mapList,strategyMap,array3,3);
        getAndSetList(mapList,strategyMap,array4,4);
        getAndSetList(mapList,strategyMap,array5,5);
        getAndSetList(mapList,strategyMap,array6,6);
        getAndSetList(mapList,strategyMap,array7,7);
        result.put("month",monthArray);
        result.put("data",mapList);
        return result;
    }

    private void getAndSetSmokeList(List<Map<String, Object>> mapList,String strategy,List<Integer> array,int i) {
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("name",strategy);
        obj.put("value",array);
        mapList.add(obj);
    }
    private void getAndSetList(List<Map<String, Object>> mapList,Map<Integer, String> strategyMap,List<Integer> array,int i) {
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("name",strategyMap.get(i));
        obj.put("value",array);
        mapList.add(obj);
    }

    private Map<String,Object> setSmokeList(ScreenVo vo) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> mapList = new ArrayList<>();

        List<ScreenVo> monthList = dateService.getMonth();

        List<String> monthArray = new ArrayList<>();
        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();

        monthList.stream().forEach(e->{
            vo.setStartTime(e.getStartTime());
            vo.setEndTime(e.getEndTime());
            List<ScreenVo> list = deviceMessageScreenMapper.eventNumByMonthSmoke(vo);
            Map<String,Integer> map = list.stream().collect(Collectors.toMap(ScreenVo::getEventCode,ScreenVo::getCountNum,(key1, key2)->key2));

            array1.add(map.get("1"));
            array2.add(map.get("2"));

            String month =  dtf.format(e.getStartTime());
            monthArray.add(month);

        });
        getAndSetSmokeList(mapList,"其他故障",array1,1);
        getAndSetSmokeList(mapList,"报警",array2,2);

        result.put("month",monthArray);
        result.put("data",mapList);

        return result;
    }


    private List<Map<String,Object>> setWaterpressList(ScreenVo vo) {
        List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM");
        List<ScreenVo> monthList = dateService.getMonth();
        monthList.stream().forEach(e->{
            vo.setStartTime(e.getStartTime());
            vo.setEndTime(e.getEndTime());
            List<ScreenVo> list = deviceMessageScreenMapper.eventNumByMonthWaterpress(vo);
            Map<String,Object> map = list.stream().collect(Collectors.toMap(ScreenVo::getEventCode,ScreenVo::getCountNum,(key1, key2)->key2));
            String month =  dtf.format(e.getStartTime());
            map.put("month",month);
            maplist.add(map);
        });
        return maplist;
    }

    private void calTotalNum(List<ScreenVo> list) {
        AtomicInteger totalNum = new AtomicInteger();
        list.forEach(e -> {
            totalNum.addAndGet(e.getCountNum());
        });
        if (totalNum != null && totalNum.get() > 0) {
            list.stream().forEach(e -> {
                e.setPercentNum((float) e.getCountNum() / totalNum.get());
                e.setTotalNum(totalNum.get());
            });
        }
    }

}
