package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmWaterpressMapper;
import com.smart.device.message.data.service.TAlarmWaterpressService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class TAlarmWaterpressServiceImpl extends ServiceImpl<TAlarmWaterpressMapper, TAlarmWaterpress> implements TAlarmWaterpressService {

    @Resource
    private TAlarmWaterpressMapper alarmWaterpressMapper;

    @Override
    public IPage<AlarmVo> alarmWaterpressList(PageDomain page, DeviceCompanyVo vo) {
        Page<AlarmVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<AlarmVo> iPage = alarmWaterpressMapper.alarmWaterpressList(pg,vo);
        return iPage;
    }
    @Override
    public IPage<DeviceCompanyVo> alarmWaterpressListUser(PageDomain page, DeviceCompanyVo vo) {
        Page<DeviceCompanyVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<DeviceCompanyVo> iPage = alarmWaterpressMapper.alarmWaterpressListUser(pg,vo);
        return iPage;
    }

    @Override
    public int waterpressAdd(TAlarmWaterpress tAlarmWaterpress) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmWaterpress.setId(idWorker.nextId());
        this.save(tAlarmWaterpress);
        return 0;
    }

    @Override
    public AlarmVo getLastWaterpressAlarm(DeviceCompanyVo vo) {
        AlarmVo res = alarmWaterpressMapper.getLastWaterpressAlarm(vo);
        return res;
    }
}
