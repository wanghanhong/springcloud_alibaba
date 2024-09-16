package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TAlarmInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TAlarmInfoMapper extends BaseMapper<TAlarmInfo> {

    IPage<TAlarmInfo> tAlarmInfoList(Page<TAlarmInfo> page, @Param("vo")TAlarmInfo vo);

}
