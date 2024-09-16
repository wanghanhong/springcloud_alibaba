package com.smart.system.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.system.manage.entity.SysUser;
import com.smart.system.manage.entity.SysUserPost;
import com.smart.system.manage.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 关联用户岗位
     *
     * @param list
     * @return
     */
    int batchInsertUserPost(List<SysUserPost> list);

    /**
     * 关联用户角色
     *
     * @param list
     */
    void batchInsertUserRole(List<SysUserRole> list);

    /**
     * 删除用户岗位
     *
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_post where user_id = #{userId}")
    boolean removeUserPost(@Param("userId") Long userId);

    /**
     * 删除用户角色
     *
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    boolean removeUserRole(Long userId);

    /**
     * 根据用户Id查询角色
     *
     * @param userId
     * @return
     */
    @Select("select role_id from sys_user_role where user_id = #{userId}")
    Set<Long> selectUserRoleByUserId(String userId);

    /**
     * 根据用户Id查询岗位
     *
     * @param userId
     * @return
     */
    @Select("select post_id from sys_user_post where user_id = #{userId}")
    Set<Long> selectUserPostByUserId(String userId);
}
