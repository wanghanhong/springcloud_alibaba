package com.smart.message.manage.mq;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.mq.consumer.BroadcastConsumer;
import com.smart.message.manage.mq.producer.SyncProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;

/**
 * USER: gll
 * DATE: 2020/5/6
 * TIME: 12:48
 * Describe:
 */
@RestController
@RequestMapping("/api/v2/mq")
public class ProduceController {
    @Autowired
    SyncProducer syncProducer;
    @Autowired
    BroadcastConsumer broadcastConsumer;

    /**
     * 测试生产者
     *
     * @param yunBaseModel
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(@RequestBody YunBaseModel yunBaseModel) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        syncProducer.send(yunBaseModel);
        return "success";
    }

    @RequestMapping(value = "/mqSend", method = RequestMethod.GET)
    public Result send(String phones, String smsParam,String phoneParam, Integer strategyType,String deviceCode){
        try {
            YunBaseModel yunBaseModel = new YunBaseModel();
            yunBaseModel.setPhone(phones);
            yunBaseModel.setSmsParam(smsParam);
            yunBaseModel.setPhoneParam(phoneParam);
            yunBaseModel.setStrategyType(strategyType);
            yunBaseModel.setDeviceCode(deviceCode);
            syncProducer.send(yunBaseModel);
//            broadcastConsumer.useStrategy(yunBaseModel);
            return Result.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MESSAGE_MANAGER_PRODUCT);
        }
    }

    /**
     * 测试消费者
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public String getInfo() throws Exception {
        broadcastConsumer.start();
        return "success";
    }

    @RequestMapping(value = "/testSMS", method = RequestMethod.GET)
    public Result testSMS(@RequestBody YunBaseModel yunBaseModel){
        try {
            broadcastConsumer.useStrategy(yunBaseModel);
            return Result.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MESSAGE_MANAGER_PRODUCT);
        }
    }
}
