package com.smart.device.install.service.monitor.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;
import com.smart.device.install.mapper.monitor.SdDeviceMonitorMapper;
import com.smart.device.install.service.monitor.SdDeviceMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SdDeviceMonitorServiceImpl extends ServiceImpl<SdDeviceMonitorMapper, DeviceMonitorVo> implements SdDeviceMonitorService {

    @Resource
    private SdDeviceMonitorMapper sdDeviceMonitorMapper;


    @Override
    public IPage<DeviceMonitorVo> deptControl(PageDomain page, DeviceMonitorVo vo) {
        List<DeviceMonitorVo> reslist = new ArrayList<>();
        try {
            Page<DeviceMonitorVo> pg = new Page<>(page.getPageNum(), page.getPageSize());;
            IPage<DeviceMonitorVo> ipg =  sdDeviceMonitorMapper.selectCompanys(pg,vo);
            reslist = ipg.getRecords();
            reslist.forEach(e->{
                Integer buildingNum = sdDeviceMonitorMapper.queryBuildNumByCompanyId(e);
                // 查询建筑物数量
                e.setBuildingNum(buildingNum);
                // 查询设备数量
                Integer fireAllNum = sdDeviceMonitorMapper.queryBuildingSonDeviceNum(e);
                e.setFireAllNum(fireAllNum);
                // 查询未处理数量
                Integer dealNum = sdDeviceMonitorMapper.queryDealNum(e);
                e.setDealNum(dealNum);

            });
            ipg.setRecords(reslist);
            ipg.setTotal(reslist.size());
            return ipg;
        } catch (Exception e) {
            log.error("获取联网监控失败", e);
            return new Page<>();
        }
    }

    @Override
    public IPage<DeviceMonitorVo> deviceAlarmsList(PageDomain page,DeviceMonitorVo vo) {
        try {
            Page<DeviceMonitorVo> pg = new Page<>(page.getPageNum(), page.getPageSize());;
            IPage<DeviceMonitorVo> list = sdDeviceMonitorMapper.deviceAlarmsList(pg,vo);
            list.getRecords().forEach(
                    // 报警处理状态0.未处理，1.被锁定，2.已处理，3.处理中，9.自动消警
                    e -> {
                        e.setStateName(convertState(e.getState()));
                    }
            );
            return list;
        } catch (Exception e) {
            log.error("获取联网监控失败", e);
            return new Page<>();
        }
    }

    // 0.未处理，1.被锁定，2.已处理，3.处理中，9.自动消警
    public String convertState(Object value){
        if (value != null) {
            String type = String.valueOf(value);
            if ( "0".equals(type) ) {
                return "未处理";
            }  else if ( "1".equals(type) ) {
                return "被锁定";
            }  else if ( "2".equals(type) ) {
                return "已处理";
            }else if ( "9".equals(type) ) {
                return "自动消警";
            }else if ( "3".equals(type) ) {
                return "处理中";
            }
        }
        return "未处理";
    }

    public static void main(String[] ags){
        Map<Long,Long> map = new HashMap<>();
        map.put(2L,1L);
        System.out.println(map.get(2L));

    }

}

