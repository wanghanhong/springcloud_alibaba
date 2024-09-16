package com.smart.device.message.data.service.screen.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.TBaseDict;
import com.smart.device.common.install.entity.vo.*;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.common.utils.DateScreenUtil;
import com.smart.device.message.data.mapper.screen.ScreenMapper;
import com.smart.device.message.data.service.TAlarmElectricService;
import com.smart.device.message.data.service.TAlarmSmokeService;
import com.smart.device.message.data.service.TAlarmWaterpressService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.data.service.screen.ScreenService;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Slf4j
@Service("screenService")
public class ScreenServiceImpl implements ScreenService {

    @Resource
    private ScreenMapper screenMapper;
    @Resource
    private TAlarmSmokeService alarmSmokeService;
    @Resource
    private TAlarmWaterpressService alarmWaterpressService;
    @Resource
    private TAlarmElectricService alarmElectricService;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;

    @Override
    public ScreenMainVo internetDept(DeviceCompanyVo vo) {
        List<Map<Integer, Object>> list = screenMapper.internetDept(vo);
        Map<Integer, Integer> result = new HashMap<>();
        list.forEach(e -> {
            result.put(Integer.parseInt(String.valueOf(e.get("type"))),Integer.parseInt(String.valueOf(e.get("num")))  );
        });
        List<TBaseDict> dicts =deviceInstallFeignClient.dictListByType("3");
        int index = dicts.size();
        String[] typeName = new String[index];
        // "综合","商业单位","居民住宅","娱乐场所","维保单位"
        for(int i= 0;i< dicts.size() ;i++){
            typeName[i] = dicts.get(i).getDictName();
        }
        Integer[] type = new Integer[index];
        int[] countNum = new int[index];
        for (int i= 1;i<= index ;i++){
            if(result.get(i) == null){
                countNum[i-1] = 0;
            }else {
                countNum[i-1] = result.get(i);
            }
            type[i-1] = i;
        }
        ScreenMainVo res = new ScreenMainVo();
        res.setTypeName(typeName);
        res.setType(type);
        res.setCountNum(countNum);
        return res;
    }

    /**
     *  统计0-24点的每个小时的报警数量
     *  累计的值统计最近一个月的东西。
     * @param vo
     * @return
     */
    @Override
    public List<Map<String, Object>> deviceNewAlarmDay(DeviceCompanyVo vo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listValue = new ArrayList<Map<String, Object>>();
        DateScreenUtil util = new DateScreenUtil();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);//当前时间减去一个月，即一个月前的时间
        Date time2 = calendar.getTime();
        String dayStart = new SimpleDateFormat("yyyy-MM-dd").format(time2);
        String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);
        try {
            vo.setToday(todayStr);
            vo.setDayStart(dayStart);
            vo.setDayEnd(todayStr);

            list = screenMapper.deviceNewAlarmDay(vo);
            list = util.changeList(list);
            map.put("name","新增告警数");
            map.put("value",getValue(list));
            listValue.add(map);
            list2 = screenMapper.deviceAlarmTotal(vo);
            list2 = util.changeList2(list2);
            map1.put("name","累计告警数");
            map1.put("value",getValue(list2));
            listValue.add(map1);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
        return listValue;
    }

    @Override
    public List<Map<String, Object>> deviceNewDay(DeviceCompanyVo vo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listValue = new ArrayList<Map<String, Object>>();
        DateScreenUtil util = new DateScreenUtil();
        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map1 = new HashMap<>();

        try {
            LocalDateTime now = LocalDateTime.now();
            //某年的第一天
            LocalDateTime firstday = now.with(TemporalAdjusters.firstDayOfYear()).withHour(0).withMinute(0).withSecond(0);
            vo.setToday(firstday.toLocalDate().toString());
            list = screenMapper.deviceNewDay(vo);
            list = util.changeMonthList(list);
            map.put("name","新增设备");
            map.put("value",getValue(list));
            map.put("bgcolor","#29A4E8");
            map.put("bordercolor","rgba(28,119,216,0.8)");
            listValue.add(map);
//            list2 = sdDeviceDao.deviceTotal(yesterday);
//            list2 = util.changeList2(list2);
//            map1.put("name","设备总数");
//            map1.put("value",getValue(list2));
//            map1.put("bgcolor","#39FFDC");
//            map1.put("bordercolor","rgba(41,164,232,0.8)");
//            listValue.add(map1);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
        return listValue;
    }

    @Override
    public List<Map<String, Object>> alarmState(DeviceCompanyVo vo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = screenMapper.alarmState(vo);
        list = getValueList(list);
        return list;
    }



    @Override
    public List<Map<String, Object>> deviceFaultDay(DeviceCompanyVo vo) {
        List listValue = new ArrayList<>();
        //当天零点
        LocalDateTime day = LocalDateTime.of(LocalDate.now().minusDays(6), LocalTime.MIN);
        Map<String, Object> list = new HashMap<String, Object>();
        Map<String, Object> list1 = new HashMap<String, Object>();
        Map<String, Object> list2 = new HashMap<String, Object>();
        try {
//            vo.setDay(day);
            list = screenMapper.deviceAlarmDay(vo);
            listValue.add(list);
            list1 = screenMapper.deviceDeptDay(vo);
            listValue.add(list1);
            list2 = screenMapper.deviceNumDay(vo);
            listValue.add(list2);
//            list = getValueFaultList(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
        return listValue;
    }

    @Override
    public List<Map<String, Object>> alarmDeptDay(DeviceCompanyVo vo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = screenMapper.alarmDeptDay(vo);
        return list;
    }

    @Override
    public List<Map<String, Object>> voiceCall(DeviceCompanyVo vo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = screenMapper.voiceCall(vo);
        } catch (Exception e) {
            return new ArrayList<Map<String, Object>>();
        }
        return list;
    }

    @Override
    public List<DeviceCompanyVo> deptStateList(DeviceCompanyVo vo) {
        return screenMapper.deptStateList(vo);
    }

    @Override
    public List<DeviceCompanyVo> deptInfoDay(DeviceCompanyVo vo) {
        return screenMapper.deptInfoDay(vo);
    }

    @Override
    public List<DeviceCompanyVo> deviceInfoDay(DeviceCompanyVo vo) {
        return screenMapper.deviceInfoDay(vo);
    }

    @Override
    public List<DeviceCompanyVo> alarmInfoDay(DeviceCompanyVo vo) {
        return screenMapper.alarmInfoDay(vo);
    }


    public List getValue(List<Map<String, Object>> map){
        List list = new ArrayList();
        map.forEach(e -> {
            list.add(e.get("num"));
        });
        return list;
    }

    public List<Map<String, Object>> getValueList(List<Map<String, Object>> lists){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,String> map = new HashMap<>();
        lists.forEach(e -> {
            if(String.valueOf(e.get("state")).equals("3")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("yeshandle",e.get("num"));
                map.put("3","3");
                list.add(mapValue);
            }
            if(String.valueOf(e.get("state")).equals("2")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("processing",e.get("num"));
                map.put("2","2");
                list.add(mapValue);
            }
            if(String.valueOf(e.get("state")).equals("0")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("nohandle",e.get("num"));
                map.put("0","0");
                list.add(mapValue);
            }
        });
         if(null == map.get("0")){
                Map<String, Object> mapValue1 =new  HashMap<>();
                mapValue1.put("nohandle",0);
                list.add(mapValue1);
            }
            if(null == map.get("2")){
                Map<String, Object> mapValue1 =new  HashMap<>();
                mapValue1.put("processing",0);
                list.add(mapValue1);
            }
            if(null == map.get("3")){
                Map<String, Object> mapValue1 =new  HashMap<>();
                mapValue1.put("yeshandle",0);
                list.add(mapValue1);
            }
       return list;
    }


    public List<Map<String, Object>> getValueFaultList(List<Map<String, Object>> lists){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,String> map = new HashMap<>();
        lists.forEach(e -> {
            if(String.valueOf(e.get("type")).equals("1")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("alarmNum",e.get("num"));
                map.put("1","1");
                list.add(mapValue);
            }
            if(String.valueOf(e.get("type")).equals("2")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("breakdownNum",e.get("num"));
                map.put("2","2");
                list.add(mapValue);
            }
            if(String.valueOf(e.get("type")).equals("3")){
                Map<String, Object> mapValue =new  HashMap<>();
                mapValue.put("reportNum",e.get("num"));
                map.put("3","3");
                list.add(mapValue);
            }
        });
        if(null == map.get("1")){
            Map<String, Object> mapValue1 =new  HashMap<>();
            mapValue1.put("alarmNum",0);
            list.add(mapValue1);
        }
        if(null == map.get("2")){
            Map<String, Object> mapValue1 =new  HashMap<>();
            mapValue1.put("breakdownNum",0);
            list.add(mapValue1);
        }
        if(null == map.get("3")){
            Map<String, Object> mapValue1 =new  HashMap<>();
            mapValue1.put("reportNum",0);
            list.add(mapValue1);
        }
        return list;
    }

    @Override
    public IPage<AlarmVo> MapDevicesDetailByID(PageDomain page, AlarmVo vo) {
            try {
                Page<AlarmVo> ipg = new Page<>(page.getPageNum(), page.getPageSize());
                IPage<AlarmVo> ipage= screenMapper.alarmDevicesList(ipg,vo);
//                List<AlarmVo> list = ipage.getRecords();
//                for(int i=0;i<list.size();i++){
//                    list.get(i).setCreateTime()
//                }

                ipage.setRecords(ipage.getRecords());
                return ipage;
            }catch (Exception e) {
                log.error("获取報警失败", e);
                return new Page<>();
            }
    }

    @Override
    public IPage<AlarmVo> MapDevicesGroupByLL(DeviceCompanyVo vo) {
        IPage<AlarmVo> page = new Page<>();
        try {
            List<AlarmVo> list = screenMapper.MapdevicesGroupByLL(vo);
            page.setRecords(list);
            page.setTotal(screenMapper.MapdevicesGroupByLLCount(vo));
            return page;
        } catch (Exception e) {
            log.error("获取设备失败", e);
            return new Page<>();
        }
    }

    @Override
    public void screenSendPhone(DeviceCompanyVo vo){
        try {
            Long imei = vo.getImei();
            Integer deviceType = vo.getParentType();
            if(deviceType != null){
                if(vo.getParentType().equals(DeviceConstant.device_type_smoke) ||
                        vo.getParentType().equals(DeviceConstant.device_type_gas)){
                    DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(imei);
                    DeviceCompanyVo companyVo = deviceInstallFeignClient.smokePerSonByDeviceId(baseVo.getId());
                    vo.setDeviceId(baseVo.getId());
                    AlarmVo alarm = alarmSmokeService.getLastSmokeAlarm(vo);
                    tStrategyAlarmService.getParamAndMQSendOnly(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                }
                if(vo.getParentType().equals(DeviceConstant.device_type_waterpress )||
                        vo.getParentType().equals(DeviceConstant.device_type_liquidlevel)){
                    DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceWaterpressByImei(imei);
                    DeviceCompanyVo companyVo = deviceInstallFeignClient.waterpressPerSonByDeviceId(baseVo.getId());
                    vo.setDeviceId(baseVo.getId());
                    AlarmVo alarm = alarmWaterpressService.getLastWaterpressAlarm(vo);
                    tStrategyAlarmService.getParamAndMQSendOnly(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                }
                if(vo.getParentType().equals(DeviceConstant.device_type_electric)){
                    DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(imei);
                    DeviceCompanyVo companyVo = deviceInstallFeignClient.electricPerSonByDeviceId(baseVo.getId());
                    vo.setDeviceId(baseVo.getId());
                    AlarmVo alarm = alarmElectricService.getLastElectricAlarm(vo);
                    tStrategyAlarmService.getParamAndMQSendOnly(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
