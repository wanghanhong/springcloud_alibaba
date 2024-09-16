package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TVaccineRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TVaccineRecordMapper extends BaseMapper<TVaccineRecord> {

    IPage<TVaccineRecord> vaccineRecordList(Page<TVaccineRecord> page, @Param("vo") TVaccineRecord vo);


}
