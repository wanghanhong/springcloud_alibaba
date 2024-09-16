package com.smart.system.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageResult;
import com.smart.system.common.entity.SysUserPost;
import com.smart.system.manage.entity.SysUser;
import org.apache.poi.ss.formula.functions.T;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据ID查询用户
     *
     * @param userId 用户Id
     * @return
     */
    SysUser query(String userId);

    /**
     * 创建用户成功
     * @return 结果标识
     * @param user
     */
    int create(SysUser user);

    /**
     * 根据用户名模糊查询
     *
     * @param page
     * @param userName
     * @return
     */
    PageResult queryByUserName(Page<T> page, String userName);

    /**
     * 根据条件查询
     * @param page
     * @param user
     * @return
     */
    PageResult listUserByCondition(Page page, SysUser user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean update(SysUser user);
}
