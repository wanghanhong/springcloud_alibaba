package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.BsVillage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface BsVillageMapper extends BaseMapper<BsVillage> {

    IPage<BsVillage> bsVillageList(Page<BsVillage> page, @Param("vo")BsVillage vo);


}
