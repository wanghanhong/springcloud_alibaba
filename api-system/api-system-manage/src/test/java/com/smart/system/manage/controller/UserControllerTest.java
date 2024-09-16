package com.smart.system.manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.system.manage.entity.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * user 测试
 *
 * @author 三多
 * @Time 2020/7/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MockHttpSession session;
    private final String BASE_URL = "/v1/sys/user";

    /**
     * 初始化
     */
    @Before
    public void setupMockMvc() {
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        Page<SysUser> page = new Page<>();
        page.setSize(5);
        page.setCurrent(1);
        SysUser user = new SysUser();
        user.setLoginName("admin");
        user.setLoginName("admin@123");
        //拦截器那边会判断用户是否登录，所以这里注入一个用户
        session.setAttribute("user", user);
    }

    /**
     * 手机号
     * 邮箱
     * 状态
     * 男女
     * 用户类型
     */
    @Test
    public void listCondition() throws Exception {
        SysUser user = new SysUser();
        user.setPhoneNumber("187");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/list")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json.getBytes()) //传json参数
                .session(session)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
