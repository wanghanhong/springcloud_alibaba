package com.smart.message.manage.mq.producer;

import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.mq.MQConstants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 生产者
 */
@Service
public class SyncProducer {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.group}")
    private String groupName;

    public void send(YunBaseModel yunBaseModel){
        DefaultMQProducer producer = new DefaultMQProducer(MQConstants.producerGroup);
        try {
            producer.setNamesrvAddr(namesrvAddr);
            producer.setInstanceName("producer");
            producer.setVipChannelEnabled(false);
            producer.setRetryTimesWhenSendFailed(3);

            producer.start();

            String message = JSONObject.toJSONString(yunBaseModel);
            Message msg = new Message(MQConstants.TOPIC, MQConstants.TAGS, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }

    }


}
