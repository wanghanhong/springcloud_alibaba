package com.smart.device.install.service.screen.impl;

import com.baomidou.mybatisplus.core.conditions.segments.HavingSegmentList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.TBaseDict;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.device.install.mapper.TBaseDictMapper;
import com.smart.device.install.mapper.screen.DeviceInstallScreenMapper;
import com.smart.device.install.service.ITBaseDictService;
import com.smart.device.install.service.screen.DeviceInstallService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author f
 */
@Service
public class DeviceInstallServiceImpl extends ServiceImpl<DeviceInstallScreenMapper, ScreenVo> implements DeviceInstallService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private DeviceInstallScreenMapper deviceInstallScreenMapper;

    @Override
    public List<ScreenVo> deviceStateSmoke() {
        List<ScreenVo> list =  deviceInstallScreenMapper.deviceStateSmoke(DeviceConstant.device_type_smoke);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceStateGas() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceStateSmoke(DeviceConstant.device_type_gas);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceStateWaterpress() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceStateWaterpress(DeviceConstant.device_type_waterpress);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceStateLiquidlevel() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceStateWaterpress(DeviceConstant.device_type_liquidlevel);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceStateElectric() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceStateElectric(DeviceConstant.device_type_electric);
        calTotalNum(list);
        return list;
    }


    @Override
    public List<ScreenVo> deviceTypeSmoke() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceTypeSmoke(DeviceConstant.device_type_smoke);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceTypeGas() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceTypeSmoke(DeviceConstant.device_type_gas);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceTypeWaterpress() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceTypeWaterpress(DeviceConstant.device_type_waterpress);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceTypeLiquidlevel() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceTypeWaterpress(DeviceConstant.device_type_liquidlevel);
        calTotalNum(list);
        return list;
    }
    @Override
    public List<ScreenVo> deviceTypeElectric() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceTypeElectric(DeviceConstant.device_type_electric);
        calTotalNum(list);
        return list;
    }

    @Override
    public List<ScreenVo> deviceSmokeNumByCompany() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceSmokeNumByCompany(DeviceConstant.device_type_smoke);
        return list;
    }

    @Override
    public List<ScreenVo> deviceGasNumByCompany() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceSmokeNumByCompany(DeviceConstant.device_type_gas);
        return list;
    }

    @Override
    public List<ScreenVo> deviceWaterpressNumByCompany() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceWaterpressNumByCompany(DeviceConstant.device_type_waterpress);
        return list;
    }

    @Override
    public List<ScreenVo> deviceLiquidlevelNumByCompany() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceWaterpressNumByCompany(DeviceConstant.device_type_liquidlevel);
        return list;
    }

    @Override
    public List<ScreenVo> deviceElectricNumByCompany() {
        List<ScreenVo> list = deviceInstallScreenMapper.deviceElectricNumByCompany(DeviceConstant.device_type_electric);
        return list;
    }

    @Override
    public List<ScreenVo> deviceBuildCompanyElectric() {
        ScreenVo vo = new  ScreenVo();
        vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
        vo.setParentType(DeviceConstant.device_type_electric);
        List<ScreenVo> list = deviceInstallScreenMapper.deviceBuildCompanyElectric(vo);
        return list;
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
