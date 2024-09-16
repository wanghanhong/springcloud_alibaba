package com.smart.system.manage.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageResult;
import com.smart.system.manage.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 用户service类测试
 * @author 三多
 * @Time 2020/7/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private ISysUserService userService;

    /**

     * 多条件查询
     * 手机号
     * 邮箱
     * 状态
     * 男女
     * 用户类型
     */
    @Test
    public void listUserByConditionTest(){
        Page<SysUser> page = new Page<>();
        page.setSize(10);
        page.setCurrent(1);
        SysUser user = new SysUser();
        //测试手机号
        user.setPhoneNumber("187");
        PageResult result = userService.listUserByCondition(page, user);
        Assert.isNull(result);
    }

}
