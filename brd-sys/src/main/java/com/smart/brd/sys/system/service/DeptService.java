package com.smart.brd.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.system.domain.po.Dept;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface DeptService extends IService<Dept> {

    Map<String, Object> findDepts(HttpServletRequest request, QueryRequest queryRequest, Dept dept);

    List<Dept> findDeptLists(HttpServletRequest request,QueryRequest queryRequest,Dept dept);

    void createDept(Dept dept);

    void updateDept(Dept dept);

    void deleteDepts(String[] deptIds);

    Dept getDeptIdByDeptName(String deptName);

    IPage<Dept> listDept(QueryRequest queryRequest, @Param("dept") Dept dept);

    Dept selectById(Long deptId);

    List<Dept> findDeptLists(Dept dept, QueryRequest request);

    List<Dept> quetyDeptLists(Dept dept, QueryRequest request);

}
