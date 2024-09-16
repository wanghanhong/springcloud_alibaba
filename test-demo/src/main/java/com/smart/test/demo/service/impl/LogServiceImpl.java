package com.smart.test.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.test.demo.event.entity.LogEntity;
import com.smart.test.demo.mapper.LogMapper;
import com.smart.test.demo.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @author 三多
 * @Time 2020/6/12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
    /**
     * 保存
     *
     * @param logEntity
     */
    @Override
    public Integer insertLog(LogEntity logEntity) {
        return this.baseMapper.insert(logEntity);
    }
}
