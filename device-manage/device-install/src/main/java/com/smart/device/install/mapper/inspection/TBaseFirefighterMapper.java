package com.smart.device.install.mapper.inspection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseFirefighter;
import org.apache.ibatis.annotations.Param;

/**
 * @author f
 */
public interface TBaseFirefighterMapper extends BaseMapper<TBaseFirefighter> {

    IPage<TBaseFirefighter> baseFirefighterList(Page<TBaseFirefighter> page, @Param("vo") TBaseFirefighter vo);

    TBaseFirefighter selectBaseFirefighter(@Param("vo") TBaseFirefighter vo);


}
