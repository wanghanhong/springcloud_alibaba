package com.smart.device.message.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.utils.Phone;
import com.smart.device.message.data.mapper.TStrategyAlarmMapper;
import com.smart.device.message.data.service.screen.ScreenService;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.feign.MessageManageFeignClient;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.ws.client.WSClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author
 */
@Service
@Slf4j
public class TStrategyAlarmServiceImpl extends ServiceImpl<TStrategyAlarmMapper, TStrategyAlarm> implements TStrategyAlarmService {

    @Resource
    private TStrategyAlarmMapper tStrategyAlarmMapper;
    @Resource
    private MessageManageFeignClient messageManageFeignClient;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Autowired
    private ScreenService screenService;


    @Override
    public IPage<TStrategyAlarm> tStrategyAlarmList(Page page, TStrategyAlarm TStrategyAlarm) {
        return null;
    }

    @Override
    public int tStrategyAlarmAdd(TStrategyAlarm tStrategyAlarm) {
        return 0;
    }

    @Override
    public TStrategyAlarm queryStrategyByTypeAndCode(Integer deviceType,String eventCode) {
        TStrategyAlarm vo = new TStrategyAlarm();vo.setDeviceType(deviceType);vo.setEventCode(eventCode);
        try {
            TStrategyAlarm strategy = tStrategyAlarmMapper.queryStrategyByTypeAndCode(vo);
            return strategy;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new TStrategyAlarm();
    }

    // 所有的报警均会走这个方法。
    @Override
    public void getParamAndMQSend(DeviceCompanyVo companyVo,Integer deviceType,String eventCode) {
        if(companyVo != null){
            if(companyVo.getIsXcx() != null && companyVo.getIsXcx() == 1){
                getContentAndSendUser(companyVo,deviceType,eventCode);
            }else{
                getContentAndSend(companyVo,deviceType,eventCode);
            }
        }
    }
    //  有单位的报警内容
    public void getContentAndSend(DeviceCompanyVo companyVo,Integer deviceType,String eventCode){
        TStrategyAlarm strategyAlarm = queryStrategyByTypeAndCode(deviceType,eventCode);
        // 定义事件
        if(strategyAlarm.getStrategyType() != null){
            companyVo.setContent(strategyAlarm.getContent());
            pushMsg();
            pushWeixinMsg(companyVo);

            List<TBaseRegion> regions = deviceInstallFeignClient.selectRegionsByCode(companyVo.getProvince(),companyVo.getCity(),companyVo.getCounty(),companyVo.getTown());
            Map<String,String> map  = getcontent(regions,companyVo);
            String smsParam = map.get("smsParam");
            String phoneParam = map.get("phoneParam");
            String phones = getPhonesString(companyVo);
            try {
                messageManageFeignClient.mqSend(phones,smsParam,phoneParam,strategyAlarm.getStrategyType(),String.valueOf(companyVo.getImei()));
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("baojing-进入"+ JSONObject.toJSONString(regions));

        }
    }
    //  个人信息的报警内容
    public void getContentAndSendUser(DeviceCompanyVo companyVo,Integer deviceType,String eventCode) {
        TStrategyAlarm strategyAlarm = queryStrategyByTypeAndCode(deviceType,eventCode);
        // 定义事件
        if(strategyAlarm.getStrategyType() != null){
            companyVo.setContent(strategyAlarm.getContent());
            pushMsg();
            pushWeixinMsgUser(companyVo);

            Map<String,String> map  = getContentUser(companyVo);
            String smsParam = map.get("smsParam");
            String phoneParam = map.get("phoneParam");
            String phones = companyVo.getPhone();
            messageManageFeignClient.mqSend(phones,smsParam,phoneParam,strategyAlarm.getStrategyType(),String.valueOf(companyVo.getImei()));

        }
    }

    //  拼电话和短信的参数。
    public Map<String,String> getContentUser(DeviceCompanyVo vo) {
        Map<String,String> map = new HashMap<>();
        String smsParam = "";
        String phoneParam =  "";
        String area = "";
        try {
            String content = "";
            area = vo.getCity()+ vo.getCounty()+ vo.getTown();
            content = vo.getCity()+","+vo.getCounty()+","+vo.getTown();
            String param2 = area;
            if(StringUtils.isNotBlank(vo.getHousing())){
                param2 += vo.getHousing();
            }
            if(StringUtils.isNotBlank(vo.getLocation())){
                param2 += vo.getLocation();
            }
            String param3 = "编号为"+vo.getImei();
            smsParam = vo.getUsername()+";"+param2+";"+param3;

            phoneParam = content + ",";
            if(StringUtils.isNotBlank(vo.getHousing())){
                phoneParam += vo.getHousing() + ",";
            }
            if(StringUtils.isNotBlank(vo.getLocation())){
                phoneParam += vo.getLocation()+ ",";
            }
            phoneParam += vo.getImei();
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("area",area);
        map.put("smsParam",smsParam);
        map.put("phoneParam",phoneParam);
        return map;
    }


    //  拼电话和短信的参数。
    public Map<String,String> getcontent(List<TBaseRegion> list,DeviceCompanyVo vo) {
        Map<String,String> map = new HashMap<>();
        String smsParam = "";
        String phoneParam =  "";
        String area = "";
        try {
            String city="";
            String country="";
            String town="";

            String content = "";
            if(list != null && list.size() == 4){
                city=list.get(1).getRegionName();
                country=list.get(2).getRegionName();
                town=list.get(3).getRegionName();
                area = city+country+town;
                content = city+","+country+","+town;
            }
            if(list != null && list.size() == 3){
                city=list.get(0).getRegionName();
                country=list.get(1).getRegionName();
                town=list.get(2).getRegionName();
                area = city+country+town;
                content = city+","+country+","+town;
            }
            String param2 = area;
            if(StringUtils.isNotBlank(vo.getBuildingName())){
                param2 += vo.getBuildingName();
            }
            String param3 = "";
            if(vo.getBuildingFloor() != null){
                param3 += vo.getBuildingFloor()+"层，";
            }
            param3 += "编号为"+vo.getImei();
            smsParam = vo.getCompanyName()+";"+param2+";"+param3;
            phoneParam = content + ",";
            if(StringUtils.isNotBlank(vo.getBuildingName())){
                phoneParam += vo.getBuildingName()+",";
            }
            if(vo.getBuildingFloor() != null){
                phoneParam += vo.getBuildingFloor()+",";
            }
            phoneParam += vo.getImei();
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("area",area);
        map.put("smsParam",smsParam);
        map.put("phoneParam",phoneParam);
        return map;
    }

    //  主要功能
    //  1 检测电话是正确
    //  去除 单次事件中重复的电话
    private String getPhonesString(DeviceCompanyVo info ) {
        Phone phone = new Phone();
        String phones =  "";
        String phone1 = info.getInchargePhone();
        String phone2 = info.getManagerPhone();
        String phone3 = info.getParttimePhone();

        if(phone.checkPhone(phone1)){
            phones += phone1;
        }
        if(phone.checkPhone(phone2)){
            if(!phone2.equals(phone1)){
                if(phones.length() > 0){
                    phones += (","+phone2);
                }else {
                    phones = phone2;
                }
            }
        }
        if(phone.checkPhone(phone3)){
            if(!phone3.equals(phone1) && !phone3.equals(phone2)  ){
                if(phones.length() > 0){
                    phones += (","+phone3);
                }else {
                    phones = phone3;
                }
            }
        }
        log.info("发短信-电话的结果{}--"+phones);
        return phones;
    }

    @Override
    public Map<Integer,String> queryStrategyMap(Integer deviceType) {
        TStrategyAlarm vo = new TStrategyAlarm();
        vo.setParentType(deviceType);
        List<TStrategyAlarm> queryStrategys = tStrategyAlarmMapper.queryStrategys(vo);
        Map<Integer, String> map = queryStrategys.stream().collect(Collectors.toMap(TStrategyAlarm::getParentEventCode,TStrategyAlarm::getEventCodeName,(key1, key2)-> key2));
        return map;
    }

    public void pushMsg(){
        try {
            // 火警，查询报警就 告警设备
            DeviceCompanyVo vo = new DeviceCompanyVo();
            // 设备故障数统计-分类统计
            List<Map<String,Object>> res = screenService.deviceFaultDay(vo);

            Map<String,Object>  map = new HashMap<String, Object>();
            map.put("data1",res);
            res = screenService.alarmState(vo);
            map.put("data2",res);
            String obj = JSONObject.toJSONString(map);

            WSClient.sendMsg(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pushWeixinMsg(DeviceCompanyVo vo){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            log.info("baojing-data1-进入"+ JSONObject.toJSONString(vo));
            Map<String,Object> res = new HashMap<>();
            Map<String,Object> map = new HashMap<>();

            List<TBaseRegion> regions = deviceInstallFeignClient.selectRegionsByCode(vo.getProvince(),vo.getCity(),vo.getCounty(),vo.getTown());
            Map<String,String> areamap = getcontent(regions,vo);
            map.put("area",areamap.get("area"));
            map.put("time",df.format(new Date()));

            map.put("deviceId",vo.getDeviceId());
            map.put("parentType",vo.getParentType());
            map.put("companyId",vo.getCompanyId());
            map.put("companyName",vo.getCompanyId());
            map.put("deptName",vo.getCompanyName());

            map.put("inchargeName",vo.getInchargeName());
            map.put("inchargePhone",vo.getInchargePhone());
            map.put("managerName",vo.getManagerName());
            map.put("managerPhone",vo.getManagerPhone());
            map.put("parttimeName",vo.getParttimeName());
            map.put("parttimePhone",vo.getParttimePhone());

            map.put("buildingName",vo.getBuildingName());
            map.put("buildingFloor",vo.getBuildingFloor());
            map.put("imei",vo.getImei());

            map.put("province",vo.getProvince());
            map.put("city",vo.getCity());
            map.put("county",vo.getCounty());
            map.put("town",vo.getTown());

            map.put("housing",vo.getHousing());
            map.put("location",vo.getLocation());

            map.put("type",vo.getDeviceStateName());
            map.put("info",vo.getContent());
            map.put("username",vo.getUsername());
            map.put("phone",vo.getPhone());


            StringBuilder sb = new StringBuilder("");
            sb.append("尊敬的用户您好，");
            if(StringUtils.isNotBlank(areamap.get("area") )){
                sb.append(areamap.get("area"));
            }
            if(StringUtils.isNotBlank(vo.getBuildingName())){
                sb.append(vo.getBuildingName());
            }
            if(vo.getBuildingFloor() != null){
                sb.append(vo.getBuildingFloor()+"层，");
            }
            sb.append("编号为").append(vo.getImei());
            sb.append("的设备存在报警信息或者出现异常情况，请安排相关责任人确认报警信息，并立即处理。");

            map.put("msg",sb.toString());
            res.put("data3",map);

            String obj = JSONObject.toJSONString(res);
            log.info("baojing-data3-进入"+ JSONObject.toJSONString(vo));

            WSClient.sendMsg(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pushWeixinMsgUser(DeviceCompanyVo vo){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String area = vo.getCity()+ vo.getCounty()+ vo.getTown();
            log.info("baojing-data1-进入"+ JSONObject.toJSONString(vo));
            Map<String,Object> res = new HashMap<>();

            Map<String,Object> map = new HashMap<>();
            map.put("time",df.format(new Date()));
            map.put("area",area);

            map.put("deviceId",vo.getDeviceId());
            map.put("parentType",vo.getParentType());
            map.put("companyId",vo.getCompanyId());
            map.put("companyName",vo.getCompanyId());

            map.put("inchargeName",vo.getInchargeName());
            map.put("inchargePhone",vo.getInchargePhone());
            map.put("managerName",vo.getManagerName());
            map.put("managerPhone",vo.getManagerPhone());
            map.put("parttimeName",vo.getParttimeName());
            map.put("parttimePhone",vo.getParttimePhone());

            map.put("buildingName",vo.getBuildingName());
            map.put("buildingFloor",vo.getBuildingFloor());
            map.put("imei",vo.getImei());

            map.put("province",vo.getProvince());
            map.put("city",vo.getCity());
            map.put("county",vo.getCounty());
            map.put("town",vo.getTown());

            map.put("housing",vo.getHousing());
            map.put("location",vo.getLocation());

            map.put("type",vo.getDeviceStateName());
            map.put("info",vo.getContent());
            map.put("username",vo.getUsername());
            map.put("phone",vo.getPhone());

            StringBuilder sb = new StringBuilder("");
            sb.append("尊敬的用户您好，");
            if(StringUtils.isNotBlank(area)){
                sb.append(area);
            }
            if(StringUtils.isNotBlank(vo.getHousing())){
                sb.append(vo.getHousing());
            }
            if(StringUtils.isNotBlank(vo.getLocation())){
                sb.append(vo.getLocation());
            }
            sb.append("，编号为").append(vo.getImei());
            sb.append("的设备存在报警信息或者出现异常情况，请安排相关责任人确认报警信息，并立即处理。");
            map.put("msg",sb.toString());
            res.put("data3",map);
            String obj = JSONObject.toJSONString(res);
            log.info("baojing-data3-进入"+ JSONObject.toJSONString(vo));
            WSClient.sendMsg(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getParamAndMQSendOnly(DeviceCompanyVo companyVo,Integer deviceType,String eventCode) {
        if(companyVo != null){
            if(companyVo.getIsXcx() != null && companyVo.getIsXcx() == 1){
                TStrategyAlarm strategyAlarm = queryStrategyByTypeAndCode(deviceType,eventCode);
                // 定义事件
                if(strategyAlarm.getStrategyType() != null){
                    Map<String,String> map  = getContentUser(companyVo);
                    String smsParam = map.get("smsParam");
                    String phoneParam = map.get("phoneParam");
                    String phones = companyVo.getPhone();
                    messageManageFeignClient.mqSend(phones,smsParam,phoneParam,strategyAlarm.getStrategyType(),String.valueOf(companyVo.getImei()));
                }
            }else{
                TStrategyAlarm strategyAlarm = queryStrategyByTypeAndCode(deviceType,eventCode);
                // 定义事件
                if(strategyAlarm.getStrategyType() != null){
                    List<TBaseRegion> regions = deviceInstallFeignClient.selectRegionsByCode(companyVo.getProvince(),companyVo.getCity(),companyVo.getCounty(),companyVo.getTown());
                    Map<String,String> map  = getcontent(regions,companyVo);
                    String smsParam = map.get("smsParam");
                    String phoneParam = map.get("phoneParam");
                    String phones = getPhonesString(companyVo);
                    messageManageFeignClient.mqSend(phones,smsParam,phoneParam,strategyAlarm.getStrategyType(),String.valueOf(companyVo.getImei()));
                }
            }
        }
    }


}
