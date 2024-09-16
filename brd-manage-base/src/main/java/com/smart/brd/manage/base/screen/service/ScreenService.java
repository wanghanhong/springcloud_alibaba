package com.smart.brd.manage.base.screen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.screen.entity.ScreenEntity;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.screen.entity.ScreenVaccine;
import com.smart.brd.manage.base.screen.entity.ScreenVo;
import com.smart.common.core.page.PageDomain;

public interface ScreenService extends IService<ScreenEntity> {

     ScreenVo brdFieldGroupByCity(ScreenEntity entity);

     ScreenVo livestockGroupByCity(ScreenEntity entity);

     ScreenVo livestockGroupBySuitable(ScreenEntity entity);

     ScreenLineVo livestockGroupByMonth(ScreenEntity entity);

     ScreenLineVo priceMonth(ScreenEntity entity);

     IPage<ScreenVaccine> screenVaccineList(PageDomain page, ScreenEntity entity);

     ScreenVo provinceData(ScreenEntity entity);

}
