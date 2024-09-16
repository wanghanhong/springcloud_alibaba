package com.smart.video.manage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.video.manage.entity.TVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 
 */
public interface TVideoMapper extends BaseMapper<TVideo> {

    IPage<TVideo> tDeviceList(Page<TVideo> page, @Param("vo") TVideo vo);

}
