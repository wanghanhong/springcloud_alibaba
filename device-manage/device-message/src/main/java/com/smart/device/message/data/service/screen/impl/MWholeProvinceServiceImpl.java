package com.smart.device.message.data.service.screen.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.device.message.data.mapper.screen.MWholeProvinceMapper;
import com.smart.device.message.data.service.screen.MWholeProvinceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @author f
 */
@Service
public class MWholeProvinceServiceImpl extends ServiceImpl<MWholeProvinceMapper, ScreenProvinceVo> implements MWholeProvinceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private MWholeProvinceMapper mWholeProvinceMapper;
    @Resource
    private DateService dateService;

    @Override
    public int alarmSmokeNum(String ids,Integer type) {
        int num =  0;
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        vo.setIds(ids);
        try {
            if(type != null && type == 2){
                num = mWholeProvinceMapper.alarmSmokeNum(vo)==null?0:mWholeProvinceMapper.alarmSmokeNum(vo);
            }else{
                num = mWholeProvinceMapper.eventSmokeNum(vo)==null?0:mWholeProvinceMapper.eventSmokeNum(vo);
            }
            }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int alarmWaterpressNum(String ids,Integer type) {
        int num =  0;
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        vo.setIds(ids);
        try {
            if(type != null && type == 2){
                num = mWholeProvinceMapper.alarmWaterpressNum(vo)==null?0:mWholeProvinceMapper.alarmWaterpressNum(vo);
            }else{
                num = mWholeProvinceMapper.eventWaterpressNum(vo)==null?0:mWholeProvinceMapper.eventWaterpressNum(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int alarmElectricNum(String ids,Integer type) {
        int num =  0;
        ScreenVo vo = dateService.setScreenVo();
        vo.setParentType(DeviceConstant.device_type_waterpress);
        vo.setIds(ids);
        try {
            if(type != null && type == 2){
                num = mWholeProvinceMapper.alarmElectricNum(vo)==null?0:mWholeProvinceMapper.alarmElectricNum(vo);
            }else{
                num = mWholeProvinceMapper.eventElectricNum(vo)==null?0:mWholeProvinceMapper.eventElectricNum(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

}
