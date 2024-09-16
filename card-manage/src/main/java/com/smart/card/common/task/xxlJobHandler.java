package com.smart.card.common.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.cardapi.entity.flow.FlowProdInfo;
import com.smart.card.cardapi.entity.query.QueryOwe;
import com.smart.card.cardapi.entity.query.QueryPakage;
import com.smart.card.cardapi.entity.query.SimInfo;
import com.smart.card.cardapi.entity.query.SimList;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.cardapi.service.FlowPoolService;
import com.smart.card.common.constant.Constant;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
import com.smart.card.manage.entity.TCard;
import com.smart.card.manage.entity.TCardBill;
import com.smart.card.manage.entity.TCardUse;
import com.smart.card.manage.entity.TPoolMember;
import com.smart.card.manage.entity.vo.TCardVo;
import com.smart.card.manage.mapper.TCardBillMapper;
import com.smart.card.manage.mapper.TCardMapper;
import com.smart.card.manage.mapper.TCardUseMapper;
import com.smart.card.manage.mapper.TPoolMemberMapper;
import com.smart.card.manage.service.ITCardService;
import com.smart.card.manage.service.ITCardUseService;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author junglelocal
 */
@Component
public class xxlJobHandler {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private BusinessQueryService businessQueryService;
    @Resource
    private FlowPoolService flowPoolService;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private TCardMapper tCardMapper;
    @Resource
    private ITCardUseService itCardUseService;
    @Resource
    private TPoolMemberMapper tPoolMemberMapper;
    @Resource
    private ITCardService itCardService;
    @Resource
    private TCardUseMapper tCardUseMapper;
    @Resource
    private TCardBillMapper tCardBillMapper;

    @XxlJob("refreshCard")
    public ReturnT<String> refreshCard(String str) {
        String key = "sim_card";
        String indexKey = "sim_page";
        long timeout = 7L;
        int indexNum = 1;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            String index = (String) redisTemplate.opsForValue().get(indexKey);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            }else {
                if (StringUtils.isNotBlank(index)) {
                    indexNum = Integer.parseInt(index) + 1;
                }
                FlowParamVo vo = new FlowParamVo();
                List<SimList> list;
                vo.setPageIndex(String.valueOf(indexNum));
                SimInfo info = businessQueryService.getSimList(vo);
                list = info.getSimList();
                TCard card = new TCard();
                for (SimList sim:list) {
                    card.setMsisdn(sim.getAccNumber());
                    card.setIccid(Long.valueOf(sim.getIccid()));
//                    card.setActivateDate(LocalDateTime.parse(sim.getCreateTime()));
                    card.setCreateTime(LocalDateTime.now());
                    card.setCustCode(Long.valueOf(sim.getCustId()));
                    card.setOffline(1);
                    card.setOfflineType("未断网");
                    card.setSimStatus(Integer.valueOf(sim.getSimStatus().get(0)));
                    card.setIsPoolMember("200");
                    String dict = tBaseDictMapper.selectNameById(String.valueOf(card.getSimStatus()));
                    card.setSimStatusName(dict);
                    card.setCustName("中国电信集团系统集成有限责任公司陕西分公司");
                    if (tCardMapper.selectById(card.getIccid()) != null) {
                        continue;
                    }
                    tCardMapper.insert(card);
                }
                if (list.isEmpty()) {
                    indexNum = 0;
                    redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
                }
                redisTemplate.opsForValue().set(indexKey, String.valueOf(indexNum), timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    @XxlJob("refreshUse")
    public ReturnT<String> refreshUse(String str) {
        String key = "card_use";
        String indexKey = "card_page";
        LocalDateTime time =  LocalDateTime.now();
        int year = time.getYear();
        int month = time.getMonthValue();
        int monthB = 10;
        String dateNow = year + "-";
        if (month < monthB) {
            dateNow += "0";
        }
        dateNow += month;
        long timeout = 7L;
        int indexNum = 1;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            String index = (String) redisTemplate.opsForValue().get(indexKey);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            }else {
                if (StringUtils.isNotBlank(index)) {
                    indexNum = Integer.parseInt(index) + 1;
                }
                Page<TCardVo> pg = new Page<>(indexNum, 15);
                TCard vo = new TCard();
                List<TCardVo> cardList = tCardMapper.tCardList(pg, vo).getRecords();
                FlowParamVo paramVo = new FlowParamVo();
                for (TCardVo cardVo:cardList) {
                    paramVo.setIccid(String.valueOf(cardVo.getIccid()));
                    paramVo.setMonthDate(dateNow);
                    List<QueryPakage> pakage = businessQueryService.queryPakage(paramVo);
                    for (QueryPakage p:pakage) {
                        TCardUse use = new TCardUse();
                        use.setIccid(cardVo.getIccid());
                        use.setPackageName(p.getOffer_name());
                        use.setStartTime(LocalDateTime.parse(paramVo.getMonthDate()));
                        use.setUsage(p.getCumulation_already() + p.getUnit_name());
                        use.setTotal(p.getCumulation_total() + p.getUnit_name());
                        use.setRest(p.getCumulation_left() + p.getUnit_name());
                        use.setResourceName(p.getAccu_name());
                        tCardUseMapper.insert(use);
                    }
                }
                if (cardList.isEmpty()) {
                    indexNum = 0;
                    redisTemplate.opsForValue().set(indexKey,Constant.TRUE, timeout, TimeUnit.DAYS);
                }
                redisTemplate.opsForValue().set(key, String.valueOf(indexNum), timeout, TimeUnit.DAYS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    @XxlJob("refreshOwe")
    public ReturnT<String> refreshOwe(String str) {
        String key = "card_product";
        String indexKey = "card_page";
        long timeout = 17L;
        int indexNum = 1;
        String flag = "1";
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            String index = (String) redisTemplate.opsForValue().get(indexKey);
            if (StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            } else {
                if (StringUtils.isNotBlank(index)) {
                    indexNum = Integer.parseInt(index) + 1;
                }
                FlowParamVo paramVo = new FlowParamVo();
                Page<TCardVo> pg = new Page<>(indexNum, 1);
                TCard vo = new TCard();
                List<TCardVo> cardList = tCardMapper.tCardList(pg, vo).getRecords();
                QueryWrapper<TCard> wrapper = new QueryWrapper<>();
                TCardBill bill = new TCardBill();
                List<TCardBill> bills;
                for (TCardVo cardVo:cardList) {
                    BeanUtils.copyBeanProp(vo, cardVo);
                    paramVo.setAccessNumber(String.valueOf(cardVo.getMsisdn()));
                    paramVo.setQueryFlag(flag);
                    List<QueryOwe> owes = businessQueryService.queryOwe(paramVo);
                    for (QueryOwe p:owes) {
                        wrapper.clear();
                        wrapper.eq("msisdn", cardVo.getMsisdn());
                        bill.setId(null);
                        bill.setMsisdn(cardVo.getMsisdn());
                        bill.setAcctCharge(setCharge(p.getAcct_item_charge()));
                        bill.setIccid(cardVo.getIccid());
                        bill.setAcctItemTypeName(p.getAcct_item_type_name());
                        bill.setAccountName(p.getAcct_name());
                        bill.setAccountId(Long.valueOf(p.getAcct_id()));
                        bill.setCreateTime(LocalDateTime.now());
                        bill.setQueryFlag("232");
                        bill.setAcctName(p.getAcct_name());
                        bill.setBillingCycleID(p.getBilling_cycle_id());
                        bill.setCycle(Integer.valueOf(p.getBilling_cycle_id()));
                        bills = tCardBillMapper.getBillByAcc(bill.getMsisdn(), bill.getCycle());
                        if (bills != null && !bills.isEmpty()) {
                            continue;
                        }
                        if (tCardBillMapper.insert(bill) <= 0) {
                            return ReturnT.FAIL;
                        }
                    }
                }
                if (cardList.isEmpty()) {
                    indexNum = 0;
                    redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
                }
                redisTemplate.opsForValue().set(indexKey, String.valueOf(indexNum), timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    public String setCharge(String charge) {
        String ch;
        int decimal = 2;
        int split;
        char[] chargeChar = charge.toCharArray();
        char[] charges = new char[charge.length() + 1];
        if (charge.length() == decimal) {
            ch = "0." + charge;
        }else {
            split = charge.length() - decimal;
            for (int i = 0; i <= charge.length(); i++) {
                if (i == split) {
                    charges[i] = '.';
                    continue;
                }
                if (i > split) {
                    charges[i] = chargeChar[i - 1];
                    continue;
                }
                charges[i] = chargeChar[i];
            }
            ch = String.copyValueOf(charges);
        }
        return ch;
    }

    @XxlJob("refreshPoolMember")
    public ReturnT<String> refreshPoolMember(String str) {
        String key = "pool_member";
        String indexKey = "pool_page";
        long timeout = 7L;
        int indexNum = 1;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            String index = (String) redisTemplate.opsForValue().get(indexKey);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            }else {
                if (StringUtils.isNotBlank(index)) {
                    indexNum = Integer.parseInt(index) + 1;
                }
                FlowParamVo vo = new FlowParamVo();
                vo.setPoolNbr("21000000012680");
                vo.setCurrentPage(String.valueOf(indexNum));
                List<FlowProdInfo> memberInfo = flowPoolService.getPoolMemberList(vo);
                TPoolMember mem = new TPoolMember();
                for (FlowProdInfo member:memberInfo) {
                    mem.setPoolNbr(21000000012680L);
                    String date = member.getEff_date();
                    date = date.replace(' ', 'T');
                    mem.setActiveTime(LocalDateTime.parse(date));
                    mem.setSubsId(Long.valueOf(member.getAcc_nbr()));
                    mem.setCreateTime(LocalDateTime.now());
                    mem.setLimitValue(Integer.valueOf(member.getFlow_quota()));
                    mem.setLimitType(Integer.parseInt(member.getQuota_type()) + 100);
                    mem.setAreaName("西安市");
                    mem.setStatusDic(member.getState());
                    if (tPoolMemberMapper.getMemberByAcc(Long.valueOf(member.getAcc_nbr())) != null) {
                        itCardService.updatePoolStatus(member.getAcc_nbr(), "21000000012680");
                        tPoolMemberMapper.updateById(mem);
                        continue;
                    }
                    if (itCardService.updatePoolStatus(member.getAcc_nbr(), "21000000012680")) {
                        tPoolMemberMapper.insert(mem);
                    }
                }
                if (memberInfo.isEmpty()) {
                    indexNum = 0;
                    redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
                }
                redisTemplate.opsForValue().set(indexKey, String.valueOf(indexNum), timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    @XxlJob("refreshCardProduct")
    public ReturnT<String> refreshCardProduct(String str) {
        String key = "card_product";
        String indexKey = "card_page";
        long timeout = 17L;
        int indexNum = 1;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            String index = (String) redisTemplate.opsForValue().get(indexKey);
            if (StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            } else {
                if (StringUtils.isNotBlank(index)) {
                    indexNum = Integer.parseInt(index) + 1;
                }
                Page<TCardVo> pg = new Page<>(indexNum, 1);
                TCard vo = new TCard();
                List<TCardVo> cardList = tCardMapper.tCardList(pg, vo).getRecords();
                for (TCardVo cardVo:cardList) {
                    BeanUtils.copyBeanProp(vo, cardVo);
                    itCardService.refreshProduct(vo);
                }
                if (cardList.isEmpty()) {
                    indexNum = 0;
                    redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
                }
                redisTemplate.opsForValue().set(indexKey, String.valueOf(indexNum), timeout, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}
