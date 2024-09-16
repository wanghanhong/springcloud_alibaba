package com.smart.brd.manage.base.screen.service;


import com.smart.brd.manage.base.screen.entity.ScreenEntity;
import java.util.List;

public interface ScreenRedisService{

     List<ScreenEntity> brdFieldGroupByCity();

     List<ScreenEntity> livestockGroupByCity();

     List<ScreenEntity> livestockGroupBySuitable();


}
