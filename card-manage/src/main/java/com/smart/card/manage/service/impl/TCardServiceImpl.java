package com.smart.card.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.cardapi.entity.prods.FunProdInfo;
import com.smart.card.cardapi.entity.prods.ProdInfo;
import com.smart.card.cardapi.entity.prods.ProdOfferInfo;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.common.constant.Constant;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
import com.smart.card.manage.entity.TCard;
import com.smart.card.manage.entity.TCardProduct;
import com.smart.card.manage.entity.TCardProductSub;
import com.smart.card.manage.entity.vo.TCardVo;
import com.smart.card.manage.mapper.TCardMapper;
import com.smart.card.manage.mapper.TCardProductMapper;
import com.smart.card.manage.mapper.TCardProductSubMapper;
import com.smart.card.manage.service.ITCardService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 
 */
@Service
public class TCardServiceImpl extends ServiceImpl<TCardMapper, TCard> implements ITCardService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardMapper tCardMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private TCardProductMapper tCardProductMapper;
    @Resource
    private TCardProductSubMapper tCardProductSubMapper;
    @Resource
    private BusinessQueryService businessQueryService;

    private static final Long TIMEOUT = 28L;

    @Override
    public IPage<TCardVo> tCardList(PageDomain page, TCard vo) {
        Page<TCardVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardVo> iPage = tCardMapper.tCardList(pg,vo);
        List<TCardVo> list = iPage.getRecords();
        String dictId;
        String dict;
        for (TCardVo card:list) {
            dictId = card.getIsPoolMember();
            dict = tBaseDictMapper.selectNameById(dictId);
            if (StringUtils.isNotNull(dict)) {
                card.setIsPoolMember(dict);
            }
            if ("0".equals(card.getIsPoolMember()) || card.getIsPoolMember() == null) {
                card.setIsPoolMember("否");
            }
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public TCard tCardAdd(TCard entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getIccid() == null) {
            entity.setIccid(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else {
            tCardUpdate(entity);
        }
        if(entity.getIccid() == null) {
            entity.setIccid(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else {
            tCardUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardDel(Long id) {
        int res = tCardMapper.deleteById(id);
        return res;
    }

    @Override
    public TCard tCardUpdate(TCard entity) {
        tCardMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCard tCardDetail(TCard entity) {
        TCard detail = tCardMapper.selectById(entity.getIccid());
//        refreshProduct(detail);
        return detail;
    }

    @Override
    public TCard tCardRemain(TCard entity) {
        QueryWrapper<TCard> wrapper = new QueryWrapper<>();
        if(entity.getMsisdn() != null){
            wrapper.eq("msisdn", entity.getMsisdn());
        }
        if(entity.getIccid() != null){
            wrapper.eq("iccid", entity.getIccid());
        }
        TCard detail = tCardMapper.selectOne(wrapper);
        return detail;
    }

    @Override
    public Boolean updatePoolStatus(String acc, String pool) {
        TCard card = tCardMapper.getCardByAcc(Long.valueOf(acc));
        if (Objects.nonNull(card)) {
            card.setIsPoolMember("201");
            card.setOwnedPoolNumber(Long.valueOf(pool));
            card.setOwnedPoolType("后向流量池");
            return tCardMapper.updateById(card) > 0;
        }
        return false;
    }

    @Override
    public List<DictDto> tCardStatus() {
        String dictType = "dict_type_227";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    @Override
    public List<DictDto> tTags() {
        String dictType = "dict_type_228";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    @Override
    public List<DictDto> tPoolMember() {
        String dictType = "dict_type_303";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public void refreshProduct(TCard card) {
        String key = "card_product_" + card.getMsisdn();
        try {
            String value = redisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(value) && value.equals(Constant.TRUE)) {
                System.out.println("success");
            }else {
                FlowParamVo vo = new FlowParamVo();
                Long msisdn = card.getMsisdn();
                vo.setAccessNumber(String.valueOf(msisdn));
                ProdInfo info = businessQueryService.queryProducts(vo);
                TCardProduct product = new TCardProduct();
                TCardProductSub productSub = new TCardProductSub();
                card.setCustName(info.getCustName());
                card.setProductName(info.getProductName());
                card.setRegistrationLocation(info.getCommonRegionName());
                tCardMapper.updateById(card);
                List<ProdOfferInfo> offerInfos = info.getProdOfferInfos();
                product.setIccid(card.getIccid());
                product.setMsisdn(card.getMsisdn());
                product.setCustName(info.getCustName());
                product.setCreateTime(LocalDateTime.now());
                for (ProdOfferInfo offer:offerInfos) {
                    product.setName(offer.getProdOfferName());
                    product.setEffectiveDate(LocalDateTime.parse(offer.getStartDt()));
                    product.setExpiredDate(LocalDateTime.parse(offer.getEndDt()));
                    product.setStatus(1);
                    tCardProductMapper.insert(product);
                }
                List<FunProdInfo> funProdInfos = info.getFunProdInfos();
                productSub.setIccid(card.getIccid());
                productSub.setMsisdn(card.getMsisdn());
                productSub.setCustName(info.getCustName());
                productSub.setCreateTime(LocalDateTime.now());
                for (FunProdInfo fun:funProdInfos) {
                    productSub.setName(fun.getProductName());
                    tCardProductSubMapper.insert(productSub);
                }
                redisTemplate.opsForValue().set(key,Constant.TRUE, TIMEOUT, TimeUnit.DAYS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
