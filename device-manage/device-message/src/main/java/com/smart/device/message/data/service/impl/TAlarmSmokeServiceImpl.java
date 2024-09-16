package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmGas;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmSmokeMapper;
import com.smart.device.message.data.service.TAlarmSmokeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author
 */
@Service
public class TAlarmSmokeServiceImpl extends ServiceImpl<TAlarmSmokeMapper, TAlarmSmoke> implements TAlarmSmokeService {

    @Resource
    private TAlarmSmokeMapper alarmSmokeMapper;

    @Override
    public IPage<AlarmVo> alarmSmokeList(PageDomain page, DeviceCompanyVo vo) {
        Page<AlarmVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<AlarmVo> iPage = alarmSmokeMapper.alarmSmokeList(pg,vo);
        return iPage;
    }

    @Override
    public int smokeAdd(TAlarmSmoke tAlarmSmoke) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmSmoke.setId(idWorker.nextId());
        this.save(tAlarmSmoke);
        return 0;
    }

    @Override
    public IPage<DeviceCompanyVo> alarmSmokeListUser(PageDomain page, DeviceCompanyVo vo) {
        Page<DeviceCompanyVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<DeviceCompanyVo> iPage = alarmSmokeMapper.alarmSmokeListUser(pg,vo);
        return iPage;
    }
    @Override
    public AlarmVo getLastSmokeAlarm(DeviceCompanyVo vo) {
        AlarmVo res = alarmSmokeMapper.getLastSmokeAlarm(vo);
        return res;
    }

}
