package com.smart.test.demo.controller;

import com.smart.common.core.domain.Result;
import com.smart.test.demo.config.JwtConfig;
import com.smart.test.demo.service.message.RocketmqProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 三多
 * @Time 2020/4/28
 */
@RestController
public class DemoController {
    @Autowired
    JwtConfig jwtConfig;

    /**
     * 刷新和nacos测试
     * 测试路径 @see http://localhost:10000/getMyProperties
     *
     * @return
     */
    @GetMapping("/getMyProperties")
    public Result testProperties() {
        System.out.println(jwtConfig);
        return Result.SUCCESS(jwtConfig);
    }

    @Autowired
    RocketmqProducer rocketmqProducer;

    /**
     * MQ 测试
     *
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        rocketmqProducer.send("test rocketmq message");
        return "success";
    }

}
