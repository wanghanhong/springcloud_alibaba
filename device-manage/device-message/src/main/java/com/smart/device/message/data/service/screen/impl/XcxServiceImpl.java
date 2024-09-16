package com.smart.device.message.data.service.screen.impl;

import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmElectricMapper;
import com.smart.device.message.data.mapper.TAlarmSmokeMapper;
import com.smart.device.message.data.mapper.TAlarmWaterpressMapper;
import com.smart.device.message.data.mapper.screen.XcxMapper;
import com.smart.device.message.data.service.screen.XcxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
public class XcxServiceImpl implements XcxService {

  @Resource
  private XcxMapper  xcxMapper;
  @Resource
  private TAlarmSmokeMapper alarmSmokeMapper;
  @Resource
  private TAlarmWaterpressMapper alarmWaterpressMapper;
  @Resource
  private TAlarmElectricMapper alarmElectricMapper;

  @Override
  public void handleAlarm(AlarmVo vo) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    vo.setCreateTime(df.format(new Date()));
    if(vo.getState() != null  ){
      if(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL.equals(vo.getState())){
        vo.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
      }else {
        vo.setState(DeviceConstant.DEVICE_DEAL_STATUS_YESHANDEL);
        vo.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_YESHANDEL_NAME);
      }
    }else{
      vo.setState(DeviceConstant.DEVICE_DEAL_STATUS_YESHANDEL);
      vo.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_YESHANDEL_NAME);
    }
    if(vo.getParentType().equals(DeviceConstant.device_type_smoke) ||
            vo.getParentType().equals(DeviceConstant.device_type_gas)){
      alarmSmokeMapper.updateAlarmSmoke(vo);
    }
    if(vo.getParentType().equals(DeviceConstant.device_type_waterpress )||
            vo.getParentType().equals(DeviceConstant.device_type_liquidlevel)){
      alarmWaterpressMapper.updateAlarmWaterpress(vo);
    }
    if(vo.getParentType().equals(DeviceConstant.device_type_electric)){
      alarmElectricMapper.updateAlarmEelectric(vo);
    }
  }


}
