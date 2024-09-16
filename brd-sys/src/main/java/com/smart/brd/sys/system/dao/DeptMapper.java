package com.smart.brd.sys.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {

    IPage<Dept> findDeptPage(Page page, @Param("vo") Dept dept);

    Dept getDeptIdByDeptName(String deptName);

    Long deptNameQuery(@Param("deptName") String deptName,@Param("parentId") Long parentId);

    Long selectBrdField(@Param("vo") Dept vo);

    void insertBrdField(@Param("vo") Dept vo);

    void updateBrdField(@Param("vo") Dept vo);

    Integer deleteBrdField(Long deptId);

    // 查询下 本部门下的第一层部门，不包括子公司及以下。
    List<Dept> queryDeptSons(@Param("vo") Dept vo);


}
