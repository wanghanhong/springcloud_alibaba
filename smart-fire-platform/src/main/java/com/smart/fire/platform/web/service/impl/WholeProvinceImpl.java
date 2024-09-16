package com.smart.fire.platform.web.service.impl;

import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.fire.platform.web.entity.vo.*;
import com.smart.fire.platform.web.mapper.ScreenMapper;
import com.smart.fire.platform.web.service.IWholeProvinceService;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WholeProvinceImpl implements IWholeProvinceService {

    @Resource
    private DeviceInstallFeign deviceInstallFeignService;
    @Resource
    private DeviceMessageFeign deviceMessageFeignService;
    @Resource
    private ScreenMapper screenMapper;


    @Override
    public ScreenCount getAll() {
        ScreenCount screenCount= new ScreenCount();
        screenCount.setOrganization(deviceInstallFeignService.companyAll());
        screenCount.setPlatform(screenMapper.queryUserNum());
        screenCount.setEquipment(deviceInstallFeignService.deviceNumAll());
        return screenCount;
    }

    @Override
    public List<ScreenProvinceVo> getCity() {
        List<ScreenProvinceVo> list = deviceInstallFeignService.deviceByCity();
        return list;
    }

    @Override
    public List<WholeProvinceVo> getWholeProvinceDeviceInfo() {
        List<TBaseRegion> areas = deviceInstallFeignService.queryCitysByCode("610000");
        List<ScreenProvinceVo> smokes = deviceInstallFeignService.deviceSmokeNum();
        List<ScreenProvinceVo> gass = deviceInstallFeignService.deviceGasNum();
        List<ScreenProvinceVo> waterpresss = deviceInstallFeignService.deviceWaterpressNum();
        List<ScreenProvinceVo> liquidlevels = deviceInstallFeignService.deviceLiquidlevelNum();
        List<ScreenProvinceVo> electrics = deviceInstallFeignService.deviceElectricNum();

        Map<String, ScreenProvinceVo> smokeMap = smokes.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> gasMap = gass.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> waterpresssMap = waterpresss.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> liquidlevelMap = liquidlevels.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> electricMap = electrics.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));

        Map<String, TBaseRegion> areaMap = areas.stream().collect(Collectors.toMap(TBaseRegion::getRegionCode, Function.identity(), (key1, key2) -> key2));

        List<WholeProvinceVo> list = new ArrayList<WholeProvinceVo>();
        for (int i=0;i<areas.size();i++){
            String code = areas.get(i).getRegionCode();
            WholeProvinceVo vo = new WholeProvinceVo();

            vo.setName(areaMap.get(code).getRegionName());
            InstallTotal device = new InstallTotal();
            DeviceFaultInfo fault = new DeviceFaultInfo();

            int deviceNum= 0;
            int alarmNum = 0;
            int eventNum = 0;

            List<Integer[]> data = new ArrayList<>();
            Integer[]     event = new Integer[5];
            Integer[] alarm = new Integer[5];

            List<Integer> deviceType = new ArrayList<>();
            List<String> deviceTypeName = new ArrayList<>();
            List<String> faultName = new ArrayList<>();
            deviceType.add(DeviceConstant.device_type_smoke);
            deviceType.add(DeviceConstant.device_type_gas);
            deviceType.add(DeviceConstant.device_type_waterpress);
            deviceType.add(DeviceConstant.device_type_liquidlevel);
            deviceType.add(DeviceConstant.device_type_electric);
            deviceTypeName.add(DeviceConstant.device_type_smoke_name);
            deviceTypeName.add(DeviceConstant.device_type_gas_name);
            deviceTypeName.add(DeviceConstant.device_type_waterpress_name);
            deviceTypeName.add(DeviceConstant.device_type_liquidlevel_name);
            deviceTypeName.add(DeviceConstant.device_type_electric_name);
            faultName.add("报警");faultName.add("其他故障");

            if(smokeMap.get(code) != null){
                device.setDeviceSmoke(smokeMap.get(code).getCountNum());
                // 某个地区的烟感数量
                // 查询过去一个月设备报警数量
                int smoke = deviceMessageFeignService.alarmSmokeNum(smokeMap.get(code).getIds(),1);
                int smokeAlarm = deviceMessageFeignService.alarmSmokeNum(smokeMap.get(code).getIds(),2);
                fault.setDeviceSmoke(smoke);
                deviceNum += smokeMap.get(code).getCountNum();
                eventNum += smoke;
                alarmNum += smokeAlarm;
                event[0] = smoke;
                alarm[0] = smokeAlarm;
            }else{
                event[0] = 0;
                alarm[0] = 0;
            }
            if(gasMap.get(code) != null){
                device.setDeviceGas(gasMap.get(code).getCountNum());
                int gas = deviceMessageFeignService.alarmSmokeNum(gasMap.get(code).getIds(),1);
                int gasAlarm = deviceMessageFeignService.alarmSmokeNum(gasMap.get(code).getIds(),2);

                fault.setDeviceGas(gas);
                deviceNum += gasMap.get(code).getCountNum();
                eventNum += gas;
                alarmNum += gasAlarm;
                event[1] = gas;
                alarm[1] = gasAlarm;
            }else{
                event[1] = 0;
                alarm[1] = 0;
            }
            if(waterpresssMap.get(code) != null){
                device.setDeviceWaterpress(waterpresssMap.get(code).getCountNum());
                int waterpress = deviceMessageFeignService.alarmWaterpressNum(waterpresssMap.get(code).getIds(),1);
                int waterpressAlarm = deviceMessageFeignService.alarmWaterpressNum(waterpresssMap.get(code).getIds(),2);
                fault.setDeviceGas(waterpress);
                deviceNum += waterpresssMap.get(code).getCountNum();
                eventNum += waterpress;
                alarmNum += waterpressAlarm;
                event[2] = waterpress;
                alarm[2] = waterpressAlarm;
            }else{
                event[2] = 0;
                alarm[2] = 0;
            }
            if(liquidlevelMap.get(code) != null){
                device.setDeviceLiquidlevel(liquidlevelMap.get(code).getCountNum());
                int liquidlevel = deviceMessageFeignService.alarmWaterpressNum(liquidlevelMap.get(code).getIds(),1);
                int liquidlevelAlarm = deviceMessageFeignService.alarmWaterpressNum(liquidlevelMap.get(code).getIds(),2);
                fault.setDeviceLiquidlevel(liquidlevel);
                deviceNum += liquidlevelMap.get(code).getCountNum();
                eventNum += liquidlevel;
                alarmNum += liquidlevelAlarm;
                event[3] = liquidlevel;
                alarm[3] = liquidlevelAlarm;
            }else{
                event[3] = 0;
                alarm[3] = 0;
            }
            if(electricMap.get(code) != null){
                device.setDeviceElectric(electricMap.get(code).getCountNum());
                int electric = deviceMessageFeignService.alarmElectricNum(electricMap.get(code).getIds(),1);
                int electricAlarm = deviceMessageFeignService.alarmElectricNum(electricMap.get(code).getIds(),2);
                fault.setDeviceElectric(electric);
                deviceNum += electricMap.get(code).getCountNum();
                eventNum += electric;
                alarmNum += electricAlarm;
                event[4] = electric;
                alarm[4] = electricAlarm;
            }else{
                event[4] = 0;
                alarm[4] = 0;
            }
            data.add(alarm);
            data.add(event);

            fault.setData(data);
            fault.setDeviceType(deviceType);
            fault.setDeviceTypeName(deviceTypeName);
            fault.setFaultName(faultName);

            vo.setDeviceFaultInfo(fault);

            vo.setInstallTotal(device);
            int healthIndex = 100;
            if(alarmNum+eventNum != 0){
                healthIndex = Math.round((1-(float)alarmNum/(alarmNum+eventNum))*100);
            }
            vo.setHealthIndex(healthIndex);
            list.add(vo);
        }
        return list;
    }


//    String str = "[\n" +
//            "\t{\"name\":\"西安\",\n" +
//            "\t\"healthIndex\":60,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t},\n" +
//            "\t{\"name\":\"铜川市\",\n" +
//            "\t\"healthIndex\":80,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"宝鸡市\",\n" +
//            "\t\"healthIndex\":55,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"咸阳市\",\n" +
//            "\t\"healthIndex\":54,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"渭南市\",\n" +
//            "\t\"healthIndex\":77,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"延安市\",\n" +
//            "\t\"healthIndex\":88,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"汉中市\",\n" +
//            "\t\"healthIndex\":66,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":31},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"榆林市\",\n" +
//            "\t\"healthIndex\":80,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":342},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"安康市\",\n" +
//            "\t\"healthIndex\":100,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":121},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "\t{\"name\":\"商洛市\",\n" +
//            "\t\"healthIndex\":90,\n" +
//            "\t\"installTotal\":{\"deviceSmoke\":4544,\"deviceWaterpress\":3321,\"deviceLiquidlevel\":3113,\"deviceGas\":2341,\"deviceElectric\":1231},\t\n" +
//            "\t\"DeviceFaultInfo\":{\"deviceSmoke\":1,\"deviceWaterpress\":2,\"deviceLiquidlevel\":2,\"deviceGas\":4,\"deviceElectric\":5}\n" +
//            "\t}\n" +
//            "]";
}
