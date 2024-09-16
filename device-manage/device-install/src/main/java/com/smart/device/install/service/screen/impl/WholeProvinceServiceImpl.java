package com.smart.device.install.service.screen.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.install.mapper.screen.WholeProvinceMapper;
import com.smart.device.install.service.area.IBsCityService;
import com.smart.device.install.service.screen.IWholeProvinceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author f
 */
@Service
public class WholeProvinceServiceImpl extends ServiceImpl<WholeProvinceMapper, ScreenProvinceVo> implements IWholeProvinceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private WholeProvinceMapper wholeProvinceMapper;
    @Resource
    private IBsCityService iBsCityService;

    @Override
    public List<ScreenProvinceVo> deviceSmokeNum() {
        List<ScreenProvinceVo> list = wholeProvinceMapper.deviceSmokeNum(DeviceConstant.device_type_smoke);
        return list;
    }
    @Override
    public List<ScreenProvinceVo> deviceGasNum() {
        List<ScreenProvinceVo> list = wholeProvinceMapper.deviceSmokeNum(DeviceConstant.device_type_gas);
        return list;
    }
    @Override
    public List<ScreenProvinceVo> deviceWaterpressNum() {
        List<ScreenProvinceVo> list = wholeProvinceMapper.deviceWaterpressNum(DeviceConstant.device_type_waterpress);
        return list;
    }
    @Override
    public List<ScreenProvinceVo> deviceLiquidlevelNum() {
        List<ScreenProvinceVo> list = wholeProvinceMapper.deviceWaterpressNum(DeviceConstant.device_type_liquidlevel);
        return list;
    }
    @Override
    public List<ScreenProvinceVo> deviceElectricNum() {
        List<ScreenProvinceVo> list = wholeProvinceMapper.deviceElectricNum(DeviceConstant.device_type_electric);
        return list;
    }

    @Override
    public int deviceSmokeAll() {
        Integer countNum =  wholeProvinceMapper.deviceSmokeAll(DeviceConstant.device_type_smoke);
        if(countNum == null){
            countNum = 0;
        }
        return countNum;
    }
    @Override
    public int deviceGasAll() {
        Integer countNum =  wholeProvinceMapper.deviceSmokeAll(DeviceConstant.device_type_gas);
        if(countNum == null){
            countNum = 0;
        }
        return countNum;
    }
    @Override
    public int deviceWaterpressAll() {
        Integer countNum = wholeProvinceMapper.deviceWaterpressAll(DeviceConstant.device_type_waterpress);
        if(countNum == null){
            countNum = 0;
        }
        return countNum;
    }
    @Override
    public int deviceLiquidlevelAll() {
        Integer countNum = wholeProvinceMapper.deviceWaterpressAll(DeviceConstant.device_type_liquidlevel);
        if(countNum == null){
            countNum = 0;
        }
        return countNum;
    }
    @Override
    public int deviceElectricAll() {
        Integer countNum = wholeProvinceMapper.deviceElectricAll(DeviceConstant.device_type_electric);
        if(countNum == null){
            countNum = 0;
        }
        return countNum;
    }
    @Override
    public int companyAll(){
        return wholeProvinceMapper.companyAll();
    }

    @Override
    public int fireCompanyAll(){
        return wholeProvinceMapper.fireCompanyAll();
    }

    @Override
    public List<ScreenProvinceVo> deviceByCity() {
        List<TBaseRegion> areas = iBsCityService.queryCitysByCode("610000");
        List<ScreenProvinceVo> smokes = deviceSmokeNum();
        List<ScreenProvinceVo> gas = deviceGasNum();
        List<ScreenProvinceVo> waterpresss = deviceWaterpressNum();
        List<ScreenProvinceVo> liquidlevel = deviceLiquidlevelNum();
        List<ScreenProvinceVo> electrics = deviceElectricNum();

        Map<String, ScreenProvinceVo> smokeMap = smokes.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> gasMap = gas.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> waterpresssMap = waterpresss.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> liquidlevelMap = liquidlevel.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));
        Map<String, ScreenProvinceVo> electricMap = electrics.stream().collect(Collectors.toMap(ScreenProvinceVo::getCity,Function.identity(),(key1, key2) -> key2));

        List<ScreenProvinceVo> list = new ArrayList<ScreenProvinceVo>();
        for (int i=0;i<areas.size();i++){
            String code = areas.get(i).getRegionCode();
            ScreenProvinceVo vo = new ScreenProvinceVo();
            vo.setName(code);
            vo.setRegionName(areas.get(i).getRegionName());
            int count = getNum(smokeMap,code)+getNum(gasMap,code)+getNum(waterpresssMap,code)
                        +getNum(liquidlevelMap,code)+getNum(electricMap,code);
            vo.setCount(count);
            list.add(vo);
        }
        Collections.sort(list, new Comparator<ScreenProvinceVo>() {
            @Override
            public int compare(ScreenProvinceVo u1, ScreenProvinceVo u2) {
                int diff = u1.getCountNum() - u2.getCountNum();
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }
                return 0; //相等为0
            }
        });
        return list;
    }

    public int getNum(Map<String, ScreenProvinceVo> map,String code){
        ScreenProvinceVo vo = map.get(code);
        if(vo != null){
            return vo.getCountNum();
        }
        return 0;
    }
}
