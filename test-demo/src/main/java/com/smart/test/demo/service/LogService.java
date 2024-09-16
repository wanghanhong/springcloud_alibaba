package com.smart.test.demo.service;

import com.smart.test.demo.event.entity.LogEntity;

/**
 * @author 三多
 * @Time 2020/6/12
 */
public interface LogService {
    /**
     * 保存
     * @param logEntity
     */
    Integer insertLog(LogEntity logEntity);
}
