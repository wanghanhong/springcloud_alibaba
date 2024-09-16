package wlw.smart.fire.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import wlw.smart.fire.system.domain.po.Dept;

import java.util.List;

/**
 * @author Pano
 */
public interface DeptMapper extends BaseMapper<Dept> {


    Dept getDeptIdByDeptName(String deptName);

    IPage<Dept> findDeptPage(Page page, @Param("vo") Dept dept);

    Long deptNameQuery(@Param("deptName") String deptName,@Param("parentId") Long parentId);

    // 查询下 本部门下的第一层部门，不包括子公司及以下。
    List<Dept> queryDeptSons(@Param("vo") Dept vo);


}