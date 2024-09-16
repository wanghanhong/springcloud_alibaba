package com.smart.system.manage.mapper;

import com.smart.system.manage.SystemManageApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 三多
 * @Time 2020/6/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemManageApp.class)
public class SysUserMapperTest {
    @Autowired(required = false)

    @Test
    public void selectUserList() {

    }
}
