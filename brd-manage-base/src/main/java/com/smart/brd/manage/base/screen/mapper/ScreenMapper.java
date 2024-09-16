package com.smart.brd.manage.base.screen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.screen.entity.ScreenVaccine;
import com.smart.brd.manage.base.screen.entity.ScreenEntity;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ScreenMapper extends BaseMapper<ScreenEntity> {

    List<ScreenEntity> brdFieldGroupByCity();
    List<ScreenEntity> livestockGroupByCity();
    List<ScreenEntity> livestockGroupBySuitable();
    List<ScreenEntity> livestockMonth(@Param("vo") ScreenEntity vo);
    List<ScreenEntity> livestockOutMonth(@Param("vo") ScreenEntity vo);

    IPage<ScreenVaccine> screenVaccineList(Page<ScreenEntity> page, @Param("vo") ScreenEntity vo);

    List<ScreenEntity> sheOutGroupByCity();
}
