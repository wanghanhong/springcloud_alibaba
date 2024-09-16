package com.smart.device.access.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import com.smart.device.access.common.utils.DeviceCommonUtils;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;
import com.smart.device.access.mapper.TDeviceCamerasMapper;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.service.BaseDeviceFactory;
import com.smart.device.access.service.ITDeviceCamerasService;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author f
 */
@Service
@Slf4j
public class TDeviceCamerasServiceImpl extends ServiceImpl<TDeviceCamerasMapper, TDeviceCameras> implements ITDeviceCamerasService, BaseDeviceFactory {

    @Resource
    private TDeviceCamerasMapper tDeviceCamerasMapper;
    @Autowired
    private DeviceCommonUtils deviceCommonUtils;
    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    @Override
    public DeviceBaseVo selectDeviceCameras(String sn) {
        DeviceBaseVo vo = new DeviceBaseVo();
        vo.setSn(sn);
        DeviceBaseVo deviceBaseVo = tDeviceCamerasMapper.selectDeviceCameras(vo);
        return deviceBaseVo;
    }

    @Override
    public Result saveDeviceInfo(List<ImportDeviceVO> successList) {
        List<Long> lstDeviceImei = tDeviceCamerasMapper.findCamerasDevice();
        List<TDeviceCameras> CamerasDeviceList = new ArrayList<>();
        if (successList.size() > 0) {
            for (ImportDeviceVO sdDevice : successList) {
                //校验imei的合法性,唯一性,只导入新增的设备数据         亲
                if (lstDeviceImei != null && lstDeviceImei.contains(sdDevice.getImei())) {
                    continue;
                }
                TDeviceCameras tDeviceCameras = new TDeviceCameras();
                Long id = 0L;
                if (StringUtils.isNotEmpty(sdDevice.getProtocol()) && StringUtils.isNotEmpty(sdDevice.getProductName()) && null != sdDevice.getImei()) {
                    id = deviceCommonUtils.getDeviceId(sdDevice.getDeviceType(), sdDevice.getProtocol(), sdDevice.getProductName(), sdDevice.getImei(),sdDevice.getDeviceModel());
                    List<Long> lstDeviceIds = tDeviceCamerasMapper.findCamerasIds();
                    if (lstDeviceIds != null && lstDeviceIds.contains(id)) {
                        continue;
                    }
                } else {
                    return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "保存数据失败，请输入必填字段");
                }
                setDeviceAttr(sdDevice, tDeviceCameras);
                tDeviceCameras.setId(id);
                tDeviceCameras.setDeviceCode(id);
                CamerasDeviceList.add(tDeviceCameras);
            }
            //插入设备表
            if (CamerasDeviceList.size() > 0) {
                log.info("导入数据到摄像头");
                this.saveBatch(CamerasDeviceList);
            }
        }
        return new Result(HttpStatus.HTTP_OK, "导入成功", true);
    }

    private void setDeviceAttr(ImportDeviceVO vo, TDeviceCameras entity) {
        BeanUtils.copyBeanProp(vo,entity);
        if(vo.getOutFactoryTime() != null){
            entity.setOutFactoryTime(new Date(Long.parseLong(vo.getOutFactoryTime())));
        }
        if(vo.getScrapTime() != null){
            entity.setScrapTime(new Date(Long.parseLong(vo.getScrapTime())));
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setDeleteFlag(0);
        entity.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
        entity.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
    }

    @Override
    public Result selectDeviceInfo(DeviceAccessVO deviceVO) {
        IPage<TDeviceCameras> page = new Page<>(deviceVO.getPageNum(),deviceVO.getPageSize());
        QueryWrapper<TDeviceCameras> wrapper = new QueryWrapper<>();
        if(null != deviceVO.getDeviceType()){
            wrapper.eq("parent_type",deviceVO.getDeviceType());
        }
        if(null != deviceVO.getDeviceCode()){
            wrapper.eq("device_code",deviceVO.getDeviceCode());
        }
        if(StringUtils.isNotEmpty(deviceVO.getDeviceName())){
            wrapper.like("device_name",deviceVO.getDeviceName());
        }
        if(null != deviceVO.getOpUserId()){
            wrapper.eq("op_user_id",deviceVO.getOpUserId());
        }
        if(null != deviceVO.getOpCompanyId()){
            wrapper.eq("op_company_id",deviceVO.getOpCompanyId());
        }
        if(null != deviceVO.getDeleteFlag()){
            wrapper.eq("delete_flag",deviceVO.getDeleteFlag());
        }
        wrapper.orderByDesc("create_time");
        IPage<TDeviceCameras> CamerasIPage = baseMapper.selectPage(page, wrapper);
        return new Result(CamerasIPage);
    }

    @Override
    public Result createCtwingDevice(DeviceAccessVO deviceVO) {
        return null;
    }

    @Override
    public void devicesDelete(Long id) {
        TDeviceCameras entity = new TDeviceCameras();
        entity.setId(id);
        entity.setDeleteFlag(1);
        tDeviceCamerasMapper.updateById(entity);
    }

}
