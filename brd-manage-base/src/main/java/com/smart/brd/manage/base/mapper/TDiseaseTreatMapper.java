package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDiseaseTreat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author
 */

public interface TDiseaseTreatMapper extends BaseMapper <TDiseaseTreat> {

    IPage <TDiseaseTreat> tDiseaseTreatList(Page <TDiseaseTreat> page, @Param("vo") TDiseaseTreat vo);

    @Select("SELECT t1.*,t2.device_code,t2.shed_name,t2.bed_name,t2.entry_date, t2.birth_date FROM t_disease_treat t1 LEFT JOIN t_livestock t2 ON t1.livestock_id=t2.livestock_id WHERE t1.delete_flag=0 AND t1.id=#{id} ")
    TDiseaseTreat diseaseTreatInfo(@Param("id") Long id);

    @Select("SELECT convert(SUM(treatment_costs),decimal(10,2)) AS treatmentCostsSum FROM t_disease_record WHERE disease_treat_id=#{diseaseTreatId}")
    BigDecimal getTreatmentCostsSum(@Param("diseaseTreatId") Long diseaseTreatId);

    @Update("update t_disease_treat set delete_flag = 1 where device_code = #{deviceCode}; ")
    int updateByDeviceCode(@Param("deviceCode") String deviceCode);
}
