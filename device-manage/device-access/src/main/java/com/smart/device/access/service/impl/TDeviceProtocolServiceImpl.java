package com.smart.device.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TDeviceProtocolMapper;
import com.smart.device.access.service.ITDeviceProtocolService;
import com.smart.device.common.access.entity.TDeviceProtocol;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
* @author 
*/
@Service
public class TDeviceProtocolServiceImpl extends ServiceImpl<TDeviceProtocolMapper, TDeviceProtocol> implements ITDeviceProtocolService {

    @Resource
    private TDeviceProtocolMapper tDeviceProtocolMapper;

    @Override
    public List<TDeviceProtocol> queryProtocolList() {
        List<TDeviceProtocol> list = tDeviceProtocolMapper.queryProtocolList();
        return list;
    }

}
