package com.smart.card.manage.service.impl;

import com.smart.card.cardapi.entity.query.QueryPakage;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.common.constant.Constant;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
import com.smart.card.manage.entity.TCardUse;
import com.smart.card.manage.mapper.TCardMapper;
import com.smart.card.manage.mapper.TCardUseMapper;
import com.smart.card.manage.service.ITCardUseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 
 */
@Service
public class TCardUseServiceImpl extends ServiceImpl<TCardUseMapper, TCardUse> implements ITCardUseService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardUseMapper tCardUseMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private TCardMapper tCardMapper;
    @Resource
    private BusinessQueryService businessQueryService;

    @Override
    public IPage<TCardUse> tCardUseList(PageDomain page, TCardUse vo) {
        Page<TCardUse> pg = new Page<>(page.getPageNum(), page.getPageSize());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(StringUtils.isNotBlank(vo.getBillDate())){
            String str = vo.getBillDate()+"-01 00:00:01";
            LocalDateTime date = LocalDateTime.parse(str, dtf);
            LocalDateTime startDateTime = date.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
            LocalDateTime endDateTime = date.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
            vo.setStartDate(startDateTime.toString());
            vo.setEndDate(endDateTime.toString());
        }
        refreshCardUse(String.valueOf(vo.getMsisdn()), vo.getBillDate());
        IPage<TCardUse> iPage = tCardUseMapper.tCardUseList(pg,vo);
        return iPage;
    }

    @Override
    public TCardUse tCardUseAdd(TCardUse entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardUseUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardUseDel(Long id) {
        int res = tCardUseMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardUse tCardUseUpdate(TCardUse entity) {
        tCardUseMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardUse tCardUseDetail(TCardUse entity) {
        TCardUse detail = tCardUseMapper.selectById(entity.getId());
        refreshCardUse(String.valueOf(detail.getMsisdn()), "");
        return detail;
    }

    @Override
    public List<DictDto> tCardBills() {
        String dictType = "dict_type_231";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    /**------通用方法开始结束-----------------------------------------*/
    @Override
    public ReturnT<String> refreshCardUse(String acc, String date) {
        String key = "card_use_" + acc + date;
        String billDate = date;
        long timeout = 20L;
        try {
            String value = redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                return ReturnT.SUCCESS;
            }else {
                FlowParamVo vo = new FlowParamVo();
                date = date.replace("-", "").concat("01").trim();
                vo.setAccessNumber(acc);
                vo.setMonthDate(date);
                List<QueryPakage> pakage = businessQueryService.queryPakage(vo);
                TCardUse use = new TCardUse();
                for (QueryPakage pak:pakage) {
                    use.setMsisdn(Long.valueOf(acc));
                    use.setCreateTime(LocalDateTime.now());
                    use.setIccid(tCardMapper.getIccidByAcc(Long.valueOf(acc)));
                    use.setRest(pak.getCumulation_left() + pak.getUnit_name());
                    use.setUsage(pak.getCumulation_already() + pak.getUnit_name());
                    use.setTotal(pak.getCumulation_total() + pak.getUnit_name());
                    double usage = Double.parseDouble(pak.getCumulation_already());
                    double total = Double.parseDouble(pak.getCumulation_total());
                    if (total == 0) {
                        use.setUseRate("0%");
                    }else {
                        use.setUseRate(usage / total + "%");
                    }
                    use.setResourceName(pak.getAccu_name());
                    use.setPackageName(pak.getOffer_name());
                    use.setStartTime(LocalDateTime.parse(getTime(pak.getStart_time())));
                    use.setEndTime(LocalDateTime.parse(getTime(pak.getEnd_time())));
                    use.setDate(billDate);
                    tCardUseMapper.insert(use);
                }
                redisTemplate.opsForValue().set(key,Constant.TRUE, timeout, TimeUnit.DAYS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    public String getTime(String in) {
        StringBuilder time = new StringBuilder();
        time.append(in, 0, 4);
        time.append("-");
        time.append(in, 4, 6);
        time.append("-");
        time.append(in, 6, 8);
        time.append("T");
        time.append(in, 8, 10);
        time.append(":");
        time.append(in, 10, 12);
        time.append(":");
        time.append(in, 12, 14);
        return time.toString();
    }
}
