package com.smart.message.manage.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.mq.MQConstants;
import com.smart.message.manage.strategy.SmsPhoneContext;
import com.smart.message.manage.strategy.SmsPhoneStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消费者
 */

@Service
public class BroadcastConsumer {
    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    @Value("${rocketmq.producer.group}")
    private String groupName;

    @Resource
    public SmsPhoneStrategy smsPhoneStrategy;

    public BroadcastConsumer() {
    }

    static private Map<String, String> logMap = new HashMap<>();


    public void start() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQConstants.producerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setInstanceName("consumer");
        //设置为广播模式
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 批量消费,每次拉取10条
        consumer.setConsumeMessageBatchMaxSize(10);
        // 如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅PushTopic下Tag为push的消息
        consumer.subscribe(MQConstants.TOPIC, MQConstants.TAGS);

        consumer.registerMessageListener(new MqMessageListener1());
        consumer.start();
//        consumer.shutdown();
    }


    public class MqMessageListener1 implements MessageListenerConcurrently{
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            String key = null;
            String msgId = null;
            try {
                MessageExt msg2 = msgs.get(0);
                String msgBody = new String(msg2.getBody(), RemotingHelper.DEFAULT_CHARSET);
                System.out.println("------消息处理中--------msgBody:" + msgBody);
                YunBaseModel yunBaseModel = JSONObject.parseObject(msgBody,YunBaseModel.class);
                useStrategy(yunBaseModel);
            } catch(Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }finally {
                logMap.put(key, msgId);
//             需要存储消息id
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

    }

    // 从消息列队中获取报警对象，然后调用电话和短信
    public void useStrategy(YunBaseModel yunBaseModel){
        try {
            SmsPhoneContext yunSmsContext = new SmsPhoneContext(smsPhoneStrategy);
            yunSmsContext.sendStyle(yunBaseModel);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

