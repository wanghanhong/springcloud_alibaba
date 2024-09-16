package wlw.smart.fire.utils;/*
package wlw.smart.fire.device.access.utils;

import com.szhq.data.common.util.Base64Utils;
import com.szhq.data.unity.model.DeviceDataRowSmoke;
import com.szhq.data.unity.service.W308Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class W308AccessCuiotTrans {


    @Autowired
    W308Service w308Service;

    //联通iot透传
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {"w309_access_cuiot_trans"}, groupId = "datatransfer_w309")
    public String consumerW302Data(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        try {
            if (Objects.nonNull(records)) {
                records.forEach(record -> {
                    try {
                        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
                        if (kafkaMessage.isPresent()) {
                            Object message = kafkaMessage.get();
                            log.info("W308_CUIOT_TRANS {}", message.toString());
                            dealData(message.toString());
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                });
            }
        } catch (Exception e) {
            log.error("Kafka监听异常 :" + e.getMessage(), e);
            throw e;
        } finally {
            ack.acknowledge();//手动提交偏移量
        }
        return null;
    }

    public void dealData(String data) {
        DeviceDataRowSmoke rawData = JSON.parseObject(data, DeviceDataRowSmoke.class);
        List<String> hexList = Base64Utils.decodeBase64To16(JSON.parseObject(rawData.getService().getData()).get("datavalue").toString());
        w308Service.dealData(hexList, rawData);
    }
}
*/
