package com.smart.brd.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.sys.system.domain.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.system.domain.po.User;
import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username username
     * @return user
     */
    User findByName(String username);

    /**
     * 查询用户详情，包括基本信息，用户角色，用户部门
     *
     * @param user         user
     * @param queryRequest queryRequest
     * @return IPage
     */
    IPage<User> findUserDetail(HttpServletRequest request, User user, QueryRequest queryRequest);

    /**
     * 更新用户登录时间
     *
     * @param username username
     */
    void updateLoginTime(String username) throws Exception;

    void updateLogin(String username, String openId) throws Exception;

    /**
     * 新增用户
     *
     * @param user user
     */
    void createUser(HttpServletRequest request,User user) throws Exception;

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(HttpServletRequest request,User user) throws Exception;

    /**
     * 删除用户
     *
     * @param userIds 用户 id数组
     */
    void deleteUsers(String[] userIds) throws Exception;

    /**
     * 更新个人信息
     *
     * @param user 个人信息
     */
    void updateProfile(User user) throws Exception;

    /**
     * 更新用户头像
     *
     * @param username 用户名
     * @param avatar   用户头像
     */
    void updateAvatar(String username, String avatar) throws Exception;

    /**
     * 更新用户密码
     *
     * @param username 用户名
     * @param password 新密码
     */
    void updatePassword(String username, String password, String oldPassword) throws Exception;

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password) throws Exception;

    /**
     * 重置密码
     *
     * @param usernames 用户集合
     */
    void resetPassword(String[] usernames) throws Exception;

    /**
     * 用户状态修改
     */
    void changeStatus(@Param("vo") UserVo vo) throws Exception;

    /**
     * 通过 openid 查找用户
     *
     * @param
     * @return user
     */
    User findByOpenId(String openId);

    void setUserRoles(User user, String[] roles);

    void resetUserPassWord(String username) throws Exception;

    void saveUser(User user) throws Exception;

    String getDeptIds(Long id);

    Long querySysConfig();
}
