package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.TDiseaseSub;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author junglelocal
 */
public interface TDiseaseSubMapper extends BaseMapper<TDiseaseSub> {

    List<TDiseaseSub> getDictList(@Param("code")String code);
}
