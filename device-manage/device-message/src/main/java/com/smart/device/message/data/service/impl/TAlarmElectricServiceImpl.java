package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmElectricMapper;
import com.smart.device.message.data.service.TAlarmElectricService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class TAlarmElectricServiceImpl extends ServiceImpl<TAlarmElectricMapper, TAlarmElectric> implements TAlarmElectricService {

    @Resource
    private TAlarmElectricMapper alarmElectricMapper;

    @Override
    public IPage<AlarmVo> alarmElectricList(PageDomain page, DeviceCompanyVo vo) {
        Page<AlarmVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<AlarmVo> iPage = alarmElectricMapper.alarmElectricList(pg,vo);
        return iPage;
    }
    @Override
    public IPage<DeviceCompanyVo> alarmElectricListUser(PageDomain page, DeviceCompanyVo vo) {
        Page<DeviceCompanyVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<DeviceCompanyVo> iPage = alarmElectricMapper.alarmElectricListUser(pg,vo);
        return iPage;
    }

    @Override
    public int electricAdd(TAlarmElectric tAlarmElectric) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmElectric.setId(idWorker.nextId());
        this.save(tAlarmElectric);
        return 0;
    }

    @Override
    public AlarmVo getLastElectricAlarm(DeviceCompanyVo vo) {
        AlarmVo res = alarmElectricMapper.getLastEelectricAlarm(vo);
        return null;
    }
}
