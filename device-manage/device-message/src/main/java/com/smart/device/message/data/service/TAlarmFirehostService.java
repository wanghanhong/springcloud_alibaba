package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.TAlarmFirehost;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmFirehostService extends IService<TAlarmFirehost> {

    IPage<TAlarmFirehost> firehostList(Page page, AlarmVo vo);

    int firehostAdd(TAlarmFirehost tAlarmFirehost);

}
