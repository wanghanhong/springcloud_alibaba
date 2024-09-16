package com.smart.test.demo.service.message;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

/**
 * 创建provider
 *
 * @author 三多
 * @Time 2020/4/30
 */
@Service
public class RocketmqProducer {
    public void send(String message) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("test_producer_group");
        producer.setNamesrvAddr("172.22.1.21:9876");
        producer.start();

        Message msg = new Message("test-topic", "test-tag", message.getBytes());
        producer.send(msg);
    }
}
