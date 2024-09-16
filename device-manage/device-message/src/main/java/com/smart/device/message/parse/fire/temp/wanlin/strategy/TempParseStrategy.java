package com.smart.device.message.parse.fire.temp.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.temp.constants.WanlinTempConstants;
import com.smart.device.message.parse.fire.temp.constants.WanlinTempEnum;
import com.smart.device.message.parse.fire.temp.wanlin.entity.TempEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author 三多
 * @Time 2020/4/10
 */
@Component
@Slf4j
public class TempParseStrategy implements ParseStrategy<TempEntity> {

    @Override
    public TempEntity assemblyData(String dataStr) {
        JSONObject obj = JSONObject.parseObject(dataStr);
        TempEntity entity = new TempEntity();

        entity.setClientId(obj.getString(WanlinTempConstants.CLIENT_ID));
        entity.setMac(obj.getString(WanlinTempConstants.DEVICE_ID));
        entity.setEvent(WanlinTempConstants.WANLIN_MAP.get(WanlinTempEnum.CONTENT).get(obj.getString(WanlinTempConstants.EVENT)));
        entity.setHumidHigh(obj.getString(WanlinTempConstants.HUMID_HIGH));
        entity.setHumidLow(obj.getString(WanlinTempConstants.HUMID_LOW));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        entity.setEventDate(df.format(DateUtils.covertLongToDate((obj.getLong(WanlinTempConstants.EVENT_DATE)))));
        entity.setIccid(obj.getString(WanlinTempConstants.ICCID));
        entity.setContent(obj.getString(WanlinTempConstants.CONTENT));
        entity.setHumidity(obj.getString(WanlinTempConstants.HUMID));

        log.info("万林温湿度计-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

    public static void main(String[] args) {
        String data = "{\"humidity_high\":\"\",\"humidity_low\":\"\",\"humidity\":\"\",\"mac\":\"681000000050000039\",\"event\":\"0010\",\"iccid\":\"\",\"client_id\":\"7f0000010dac000003d7\",\"eventdate\":1592379568,\"content\":\"消音成功\"}";

        TempParseStrategy strategy = new TempParseStrategy();
        TempEntity deviceEntity = strategy.assemblyData(data);
        System.out.println(deviceEntity);
    }
}
