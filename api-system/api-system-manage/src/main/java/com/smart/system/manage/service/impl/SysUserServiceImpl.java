package com.smart.system.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageResult;
import com.smart.system.manage.entity.SysUser;
import com.smart.system.manage.entity.SysUserPost;
import com.smart.system.manage.entity.SysUserRole;
import com.smart.system.manage.mapper.SysDeptMapper;
import com.smart.system.manage.mapper.SysPostMapper;
import com.smart.system.manage.mapper.SysRoleMapper;
import com.smart.system.manage.mapper.SysUserMapper;
import com.smart.system.manage.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysDeptMapper deptMapper;
    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysPostMapper postMapper;

    /**
     * 根据ID查询用户
     * 1、查缓存
     * 2、查数据库
     *
     * @param userId 用户Id
     * @return
     */
    @Override
    public SysUser query(String userId) {
        //1、查缓存
        //2、查数据库
        SysUser user = this.userMapper.selectById(userId);
        Set<Long> roleIds = this.userMapper.selectUserRoleByUserId(userId);
        user.setRoleIds(roleIds);
        Set<Long> postIds = this.userMapper.selectUserPostByUserId(userId);
        user.setPostIds(postIds);
        return user;
    }

    /**
     * 创建用户成功
     * 用户角色信息
     * 增加岗位信息
     * 增加用户信息
     *
     * @param user
     * @return 结果标识
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SysUser user) {

        //新增用户岗位关联
        insertUserPost(user);
        //新增用户角色关联
        insertUserRole(user);
        return this.userMapper.insert(user);
    }

    /**
     * 多条件查询
     * <p>
     * 手机号
     * 邮箱
     * 状态
     * 男女
     * 用户类型
     * </p>
     * 查询用户部门
     * 查询用户岗位
     * 查询用户角色
     *
     * @param page
     * @param user
     * @return
     */
    @Override
    public PageResult listUserByCondition(Page page, SysUser user) {
        LambdaQueryWrapper<SysUser> condition = Wrappers.lambdaQuery(SysUser.class)
                .like(Objects.nonNull(user.getPhoneNumber()), SysUser::getPhoneNumber, user.getPhoneNumber())
                .like(Objects.nonNull(user.getEmail()), SysUser::getEmail, user.getEmail())
                .eq(Objects.nonNull(user.getStatus()), SysUser::getStatus, user.getStatus())
                .eq(Objects.nonNull(user.getSex()), SysUser::getSex, user.getSex())
                .eq(Objects.nonNull(user.getUserType()), SysUser::getUserType, user.getUserType());
        Page<SysUser> pageResult = this.baseMapper.selectPage(page, condition);
        List<SysUser> records = pageResult.getRecords();
        if (records.size() > 0) {
            for (SysUser sysUser : records) {
                Long userId = sysUser.getUserId();
                if(Objects.nonNull(userId)){
                    //查询岗位
                    sysUser.setPostIds(this.userMapper.selectUserPostByUserId(userId.toString()));
                    //查询角色
                    sysUser.setRoleIds(this.userMapper.selectUserRoleByUserId(userId.toString()));
                }
            }
        }
        return new PageResult(pageResult);
    }

    /**
     * 修改用户部门
     * 修改用户岗位
     * 修改用户角色
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysUser user) {

        //修改用户岗位
        userMapper.removeUserPost(user.getUserId());
        insertUserPost(user);
        //修改用户角色
        userMapper.removeUserRole(user.getUserId());
        insertUserRole(user);
        return this.updateById(user);
    }

    /**
     * 新增用户岗角色关联
     *
     * @param user
     */
    private void insertUserRole(SysUser user) {
        Set<Long> roleIds = user.getRoleIds();
        if (Objects.nonNull(roleIds)) {
            List<SysUserRole> list = new ArrayList<>();
            Long userId = user.getUserId();
            for (Long roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                list.add(userRole);
            }
            if (list.size() > 0) {
                userMapper.batchInsertUserRole(list);
            }

        }
    }


    /**
     * 新增用户岗位关联
     *
     * @param user
     */
    private void insertUserPost(SysUser user) {
        Set<Long> postIds = user.getPostIds();
        if (Objects.nonNull(postIds)) {
            List<SysUserPost> list = new ArrayList<>();
            Long userId = user.getUserId();
            for (Long postId : postIds) {
                SysUserPost userPost = new SysUserPost();
                userPost.setUserId(userId);
                userPost.setPostId(postId);
                list.add(userPost);
            }
            if (list.size() > 0) {
                userMapper.batchInsertUserPost(list);
            }

        }
    }

    /**
     * 根据用户名模糊查询
     *
     * @param page
     * @param userName
     * @return
     */
    @Override
    public PageResult queryByUserName(Page page, String userName) {
        Page<SysUser> pageResult =
                this.userMapper.selectPage(page, Wrappers.<SysUser>lambdaQuery()
                        .like(StringUtils.isNotBlank(userName), SysUser::getUserName, userName));
        PageResult result = new PageResult(pageResult);

        return result;
    }
}
