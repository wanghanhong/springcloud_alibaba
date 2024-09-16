package com.smart.message.manage.yunhu.controller;

import cn.hutool.http.HttpStatus;
import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.strategy.SmsPhoneStrategy;
import com.smart.message.manage.yunhu.service.YunHuServiceInter;
import com.smart.message.manage.yunhu.service.impl.YunHuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/v2/yuhun")
public class YunHuController {

    @Resource
    private YunHuService yunHuService;
    @Resource
    YunHuServiceInter yunHuServiceInter;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    public SmsPhoneStrategy smsPhoneStrategy;

    @GetMapping("/getAccounts")
    public FebsResponse getAccounts() {
//        JSONObject obj = yunHuService.getAccounts();
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data("");
    }

    @GetMapping("/uploadText")
    public FebsResponse uploadText(String text) {
//        JSONObject obj = yunHuService.uploadText(text);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data("");
    }

    /**
     * 电话呼叫接口
     *
     * @param phones
     * @return
     */
//    @ApiOperation("电话呼叫接口")
//    @ApiImplicitParam(name = "电话号码", type = "String", value = "phones")
//    @GetMapping("/voiceNotify")
//    public FebsResponse voiceNotify(String phones) throws Exception{
////        String Str = "西安市,雁塔区,高新街道,电信大楼,18,10009309";
//        //必填项
//        String str = "西安市,雁塔区,高新街道,电信大楼,18,10009309";
////        boolean flag = BeanValidator.isNotNull(str);
//        // TODO: 2020/4/26 参数校验
//
//        JSONObject obj = yunHuService.voiceNotify(phones, str);
//
//        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(obj);
//    }

    /**
     * 电话状态回调接口
     *
     * @param param
     * @return
     */
    @PostMapping("/voicenotify")
    public String voicenotifyBack(@RequestBody String param) {
//        String str = yunHuServiceInter.voicenotifyBack(param);
        return "";
    }


    @GetMapping("/testRedis")
    public String testRedis() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
//        String phonepx  = "phone18736578569860374051096202";
//        String exist = (String)redisTemplate.opsForValue().get(phonepx);
//        log.info("检查exist是否标记成功--"+exist+"--"+sdf.format(new Date()));
//        if (!"1".equals(exist)){
//            //将 标记 放入到redis中，一分钟 发一次短信
//            redisTemplate.opsForValue().set(phonepx,"1",90, TimeUnit.SECONDS);
//            log.info("不存在保存--");
//        }
//        String exist2 = (String)redisTemplate.opsForValue().get(phonepx);
//        log.info("检查exist是否标记成功-第二次--"+exist2+"--"+sdf.format(new Date()));
        return "";
    }

    @PostMapping("/phoneSmsUser")
    public String voicenotifyBack(@RequestBody YunBaseModel yunBaseModel) {
        /*SmsPhoneContext yunSmsContext = new SmsPhoneContext(smsPhoneStrategy);
        yunSmsContext.sendStyle(yunBaseModel);*/
        return "";
    }


}
