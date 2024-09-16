package com.smart.brd.manage.base.common.dict;

import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author junglelocal
 */
@Component
public class DictCacheTask {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DictRedisService dictRedisService;

    /**
     * 每隔10分钟触发刷新
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void refresh() {
        String key = "dict_satus";
        Long timeout = 15L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(BrdConstant.TRUE)){
            }else{
                DictCache.toData();
                redisTemplate.opsForValue().set(key,BrdConstant.TRUE,timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("已更新字典内容");
    }

    // 每隔 24 小时触发
    @Scheduled(cron = "0 0 */24 * * ?")
    public void execute() {
        String key = "dict_type_satus";
        Long timeout = 25L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(BrdConstant.TRUE)){
            }else{
                dictRedisService.DictToRedis();
                redisTemplate.opsForValue().set(key,BrdConstant.TRUE,timeout, TimeUnit.HOURS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        logger.info("定时任务开始");
    }

}
