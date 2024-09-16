package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDiseaseRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author
 */

public interface TDiseaseRecordMapper extends BaseMapper <TDiseaseRecord> {

    IPage <TDiseaseRecord> tDiseaseRecordList(Page <TDiseaseRecord> page, @Param("vo") TDiseaseRecord vo);

    @Select("SELECT t.*,convert(unit_price*dose,decimal(10,2)) AS treatmentCosts FROM t_disease_record t WHERE disease_treat_id=#{id} ORDER BY t.treatment_time DESC ")
    List <TDiseaseRecord> selectRecordList(@Param("id")Long id);
}
