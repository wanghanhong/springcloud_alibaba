package com.smart.brd.manage.base.common.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.entity.TLivestock;
import com.smart.brd.manage.base.entity.TLivestockShedentry;
import com.smart.brd.manage.base.mapper.THeartDeviceMapper;
import com.smart.brd.manage.base.mapper.TLivestockMapper;
import com.smart.brd.manage.base.mapper.TLivestockShedentryMapper;
import com.smart.brd.manage.base.service.ITLivestockService;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class LivestockTask {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ITLivestockService livestockService;
    @Resource
    private THeartDeviceMapper tHeartDeviceMapper;
    @Resource
    private TLivestockShedentryMapper tLivestockShedentryMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;



    /**
     * 每隔 2 小时 把1、出栏 日期自动计算的，过了这个日期后不显示，自己编辑填写的，过了这个日期依然显示
     */
    @Scheduled(cron = "0 0 */24 * * ?")
    public void refresh() {
        String key = "livestock_";
        Long timeout = 2L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(BrdConstant.TRUE)){
            }else{
                livestockService.preTransferList();
                livestockService.suitableListSet();
                redisTemplate.opsForValue().set(key,"true",timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("已更新字典内容");
    }
    //每四个小时更新一次温度数据
    @Scheduled(cron = "0 0 4/4 * * ?")
    public void temperatureRefresh() {
        IdWorker idWorker = new IdWorker(0, 0);
        LambdaQueryWrapper<TLivestock> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.likeLeft(TLivestock::getDeviceCode,9);
        List<TLivestock> tLivestocks = tLivestockMapper.selectList(objectLambdaQueryWrapper);
        tLivestocks.forEach(x->{
            Random rand = new Random();
            Float v = 36 + rand.nextFloat() * 3;
            Float v1 = (float) Math.round(v * 10) / 10;
            THeartDevice tags = new THeartDevice();
            tags.setId(idWorker.nextId());
            tags.setCreateTime(LocalDateTime.now());
            tags.setDeviceType(76);
            tags.setDeviceTypeName("耳标");
            tags.setDeleteFlag(0);
            tags.setDeviceCode(x.getDeviceCode());
            tags.setTemperature(v1);
            tags.setEnvTemperature(v1);
            tHeartDeviceMapper.insert(tags);
        });

    }

    /**
     * 每个月28号23:59:59将当月存栏表里现有的数据存入统计表中
     * 定时任务修改为每月的最后一天
     */
    @Scheduled(cron = "59 59 23 28 * ?")
    //@Scheduled(cron = "0 59 23 L * ?")
    public void saveShedentry(){
        String key = "saveShedentry_";
        Long timeout = 24L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(BrdConstant.TRUE)){
            }else{
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
                List<TLivestock> tLivestocks = tLivestockMapper.selectList(new QueryWrapper<TLivestock>().eq("delete_flag", 0));
                tLivestocks.forEach(tLivestock -> {
                    TLivestockShedentry shedentry = new TLivestockShedentry();
                    BeanUtils.copyBeanProp(shedentry,tLivestock);

                    shedentry.setAddTime(LocalDate.now());
                    shedentry.setMonthLivestockId(df.format(new Date())+"-"+tLivestock.getLivestockId());
                    tLivestockShedentryMapper.insert(shedentry);
                });
                redisTemplate.opsForValue().set(key,"true",timeout, TimeUnit.HOURS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
