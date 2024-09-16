package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.BsProvince;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface BsProvinceMapper extends BaseMapper<BsProvince> {

    IPage<BsProvince> bsProvinceList(Page<BsProvince> page, @Param("vo")BsProvince vo);


}
