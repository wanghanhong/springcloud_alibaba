package com.smart.card.cardapi.util;

import com.smart.card.cardapi.entity.CtAccount;
import com.smart.card.common.constant.Constant;
import com.smart.common.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class CtAccountService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public CtAccount queryAccount() {
        CtAccount account = new CtAccount();
        String keyConfirm = "haveAccount";
        String key = "account";
        long timeout = 50L;
        try {
            String confirm = (String) redisTemplate.opsForValue().get(keyConfirm);
            CtAccount value = (CtAccount) redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(confirm) && confirm.equals(Constant.TRUE)) {
                account = value;
            }else {
                account.setUserId("O5VVY4EX6Odz0215n8p239FvDJvx7euk");
                account.setPassword("4H80Nt8tbvUA64Yt");
                account.setASecret("yak5485Yc");
                account.setPaccount("29000063131");
                redisTemplate.opsForValue().set(key, account, timeout + 4, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(keyConfirm, Constant.TRUE, timeout, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }

}
