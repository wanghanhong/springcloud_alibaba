package com.smart.brd.manage.base.screen.service.impl;


import com.smart.brd.manage.base.mapper.TLivestockPriceMapper;
import com.smart.brd.manage.base.screen.entity.*;
import com.smart.brd.manage.base.screen.mapper.ScreenMapper;
import com.smart.brd.manage.base.screen.service.ScreenRedisService;

import com.smart.brd.manage.base.service.ITBaseDictService;
import com.smart.brd.manage.base.service.area.IBsCityService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;


@Service
public class ScreenRedisServiceImpl implements ScreenRedisService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private ScreenMapper screenMapper;
    @Resource
    private TLivestockPriceMapper priceMapper;
    @Resource
    private IBsCityService iBsCityService;
    @Resource
    private ITBaseDictService tBaseDictService;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<ScreenEntity> brdFieldGroupByCity() {
        String key = "brdFieldGroupByCity_";
        Long timeout = 10L;
        List<ScreenEntity> list = new ArrayList<>();
        try {
            list = (List<ScreenEntity>)redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null || list.isEmpty()){
            list =screenMapper.brdFieldGroupByCity();
            redisTemplate.opsForValue().set(key,list,timeout, TimeUnit.MINUTES);
        }
        return list;
    }

    @Override
    public List<ScreenEntity> livestockGroupByCity() {
        String key = "livestockGroupByCity_";
        Long timeout = 10L;
        List<ScreenEntity> list = new ArrayList<>();
        try {
            list = (List<ScreenEntity>)redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null || list.isEmpty()){
            list =screenMapper.livestockGroupByCity();
            redisTemplate.opsForValue().set(key,list,timeout, TimeUnit.MINUTES);
        }
        return list;
    }

    @Override
    public List<ScreenEntity> livestockGroupBySuitable() {
        String key = "livestockGroupBySuitable_";
        Long timeout = 10L;
        List<ScreenEntity> list = new ArrayList<>();
        try {
            list = (List<ScreenEntity>)redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null || list.isEmpty()){
            list =screenMapper.livestockGroupBySuitable();
            redisTemplate.opsForValue().set(key,list,timeout, TimeUnit.MINUTES);
        }
        return list;
    }




}
