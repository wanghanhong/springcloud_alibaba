package com.smart.device.access.timing;

import com.smart.common.utils.StringUtils;
import com.smart.device.access.timing.service.DeviceStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Component
public class FixedPrintTask {

    @Resource
    private DeviceStatusService deviceStatusService;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisTemplate redisTemplate;


    // 0 */5 * * * ?	每隔5分钟触发
    @Scheduled(cron = "0 */5 * * * ?")
    public void executeHigh() {
        String key = "device_status_high";
        Long timeout = 6L;
        String value = (String)redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(value) && value.equals("true")){
        }else{
            deviceStatusService.DeviceStatusHighHeart();
            redisTemplate.opsForValue().set(key,"true",timeout, TimeUnit.MINUTES);
        }
        logger.info("定时任务开始");
    }

    // 每隔12 小时触发
    @Scheduled(cron = "0 0 */12 * * ?")
    public void execute() {
        String key = "device_status_low";
        Long timeout = 13L;
        String value = (String)redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(value) && value.equals("true")){
        }else{
            deviceStatusService.DeviceStatusLowHeart();
            redisTemplate.opsForValue().set(key,"true",timeout, TimeUnit.HOURS);
        }
        logger.info("定时任务开始");
    }

    // 每隔1 小时触发
    @Scheduled(cron = "0 0 */10 * * ?")
    public void onLineDevice() {
        String key = "device_status_online";
        Long timeout = 1L;
        String value = (String)redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(value) && value.equals("true")){
        }else{
            deviceStatusService.onLineDevice();
            redisTemplate.opsForValue().set(key,"true",timeout, TimeUnit.HOURS);
        }
        logger.info("定时任务开始");
    }

}