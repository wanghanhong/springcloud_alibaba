package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseCompany;
import com.smart.device.install.entity.vo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface TBaseCompanyMapper extends BaseMapper<TBaseCompany> {

    IPage<TBaseCompany> baseCompanyList(Page<TBaseCompany> page, @Param("vo") TBaseCompany vo);

    List<TBaseCompany> selectCompanys(@Param("vo") TBaseCompany vo);

    Integer queryCompanyCanDel(@Param("companyId") Long companyId);

    TBaseCompany selectByWhere(@Param("vo") TBaseCompany vo);

    Dept selectSysDeptBy(@Param("vo") Dept vo);
    int insertSysDeptBy(@Param("vo") Dept vo);
    int updateSysDept(@Param("vo") Dept vo);
    void deleteSysDept(@Param("vo") Dept vo);


}
