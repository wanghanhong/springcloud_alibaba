package com.smart.card.common.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.card.cardapi.entity.query.QueryOwe;
import com.smart.card.cardapi.entity.query.QueryPakage;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.common.constant.Constant;
import com.smart.card.manage.entity.TCard;
import com.smart.card.manage.entity.TCardBill;
import com.smart.card.manage.entity.TCardUse;
import com.smart.card.manage.mapper.TCardMapper;
import com.smart.card.manage.mapper.TCardUseMapper;
import com.smart.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author junglelocal
 */
@Component
public class GetDataTask {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private BusinessQueryService businessQueryService;
    @Resource
    private TCardMapper tCardMapper;
    @Resource
    private TCardUseMapper tCardUseMapper;

    public void refreshUse(String iccid, String date) {
        String key = "sim_use";
        long timeout = 20L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)){
                System.out.println(value);
            }else {
                FlowParamVo vo = new FlowParamVo();
                vo.setIccid(iccid);
                vo.setMonthDate(date);
                List<QueryPakage> pakage = businessQueryService.queryPakage(vo);
                for (QueryPakage p:pakage) {
                    TCardUse use = new TCardUse();
                    use.setIccid(Long.valueOf(vo.getIccid()));
                    use.setPackageName(p.getOffer_name());
                    use.setStartTime(LocalDateTime.parse(vo.getMonthDate()));
                    use.setUsage(p.getCumulation_already() + p.getUnit_name());
                    use.setTotal(p.getCumulation_total() + p.getUnit_name());
                    use.setRest(p.getCumulation_left() + p.getUnit_name());
                    use.setResourceName(p.getAccu_name());
                    tCardUseMapper.insert(use);
                }
                redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshOwe(String access, String flag) {
        String key = "sim_owe";
        long timeout = 15L;
        try {
            String value = (String)redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)){
                System.out.println(value);
            }else {
                FlowParamVo vo = new FlowParamVo();
                vo.setAccessNumber(access);
                vo.setQueryFlag(flag);
                List<QueryOwe> owes = businessQueryService.queryOwe(vo);
                for (QueryOwe p:owes) {
                    QueryWrapper<TCard> wrapper = new QueryWrapper<>();
                    wrapper.eq("msisdn", access);
                    TCard card = tCardMapper.selectOne(wrapper);
                    card.setBalance(p.getAcct_item_charge());
                    TCardBill bill = new TCardBill();
                    bill.setMsisdn(Long.valueOf(access));
                    bill.setAcctCharge(p.getAcct_item_charge());
                }
                redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
