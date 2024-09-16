package com.smart.message.manage.sms.controller;

import cn.hutool.http.HttpStatus;
import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.sms.service.TemplateInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v2/sms/")
public class smsController {

    @Resource
    TemplateInter templateInter;

    @GetMapping("/testSend")
    public FebsResponse getAccounts(String phone,String param1,String param2,String param3) {
        Map<String, String> result = new HashMap<>();
//        try {
//            result = templateInter.sendSms(phone, param1, param2,param3);
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }


}
