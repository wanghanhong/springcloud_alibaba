package com.smart.brd.manage.base.screen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.common.dict.DictCache;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.TLivestockPrice;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.entity.dto.DictDto;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisVo;
import com.smart.brd.manage.base.mapper.TLivestockPriceMapper;
import com.smart.brd.manage.base.screen.entity.*;
import com.smart.brd.manage.base.screen.mapper.ScreenMapper;
import com.smart.brd.manage.base.screen.service.ITLivestockAnalysisService;
import com.smart.brd.manage.base.screen.service.ScreenRedisService;
import com.smart.brd.manage.base.screen.service.ScreenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.service.ITBaseDictService;
import com.smart.brd.manage.base.service.area.IBsCityService;
import com.smart.common.core.page.PageDomain;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author
 */
@Service
public class ScreenServiceImpl extends ServiceImpl<ScreenMapper, ScreenEntity> implements ScreenService{

    private static final DecimalFormat df = new DecimalFormat("0.00");//保留两位小数点
    /**
     * 测试数字常量
     *
     */
    private static final Integer TEST_NUM = 11;

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private ScreenMapper screenMapper;
    @Resource
    private TLivestockPriceMapper priceMapper;
    @Resource
    private IBsCityService iBsCityService;
    @Resource
    private ITBaseDictService tBaseDictService;
    @Resource
    private ScreenRedisService screenRedisService;
    @Resource
    private ITLivestockAnalysisService analysisService;


    @Override
    public ScreenVo brdFieldGroupByCity(ScreenEntity entity) {
        ScreenVo vo = new ScreenVo();
        List<ScreenEntity> fields = screenRedisService.brdFieldGroupByCity();
        // 统计所有的养殖场总数。
        Integer sum = fields.stream().mapToInt(ScreenEntity::getCountNum).sum();
        Map<String,ScreenEntity> map = fields.stream().collect(Collectors.toMap(ScreenEntity::getCity, Function.identity(),(key1, key2)->key2));

        List<ScreenEntity> list = new ArrayList<>();
        // 查询所有的城市。
        List<TBaseRegion> citys = iBsCityService.Citys();
        for(int i=0;i<citys.size();i++){
            TBaseRegion r = citys.get(i);
            ScreenEntity screen = new ScreenEntity();
            screen.setName(DictCache.getAreaName(r.getRegionCode()));
            Integer value = 0;
            if(map.get(r.getRegionCode()) != null){
                value = map.get(r.getRegionCode()).getCountNum();
            }
            screen.setValue(value);
            //防止转化NaN时乱码
            if (0!=sum){
                screen.setPercent(df.format((float)value/sum*100)+"%");
            }else {
                screen.setPercent("0"+"%");
            }
            list.add(screen);
        }
        vo.setList(list);
        vo.setTotal(sum);
        return vo;
    }

    @Override
    public ScreenVo livestockGroupByCity(ScreenEntity entity) {
        ScreenVo vo = new ScreenVo();
        List<ScreenEntity> fields = screenRedisService.livestockGroupByCity();
        // 统计所有的 存栏总数。
        Integer sum = fields.stream().mapToInt(ScreenEntity::getCountNum).sum();
        Map<String,ScreenEntity> map = fields.stream().collect(Collectors.toMap(ScreenEntity::getCity, Function.identity(),(key1,key2)->key2));

        List<ScreenEntity> list = new ArrayList<>();
        // 查询所有的城市。
        List<TBaseRegion> citys = iBsCityService.Citys();
        for(int i=0;i<citys.size();i++){
            TBaseRegion r = citys.get(i);
            ScreenEntity screen = new ScreenEntity();
            screen.setName(DictCache.getAreaName(r.getRegionCode()));
            Integer value = 0;
            if(map.get(r.getRegionCode()) != null){
                value = map.get(r.getRegionCode()).getCountNum();
            }
            screen.setValue(value);
            screen.setPercent(df.format((float)value/sum*100)+"%");
            list.add(screen);
        }
        vo.setList(list);
        vo.setTotal(sum);
        return vo;
    }

    @Override
    public ScreenVo livestockGroupBySuitable(ScreenEntity entity) {
        ScreenVo vo = new ScreenVo();
        List<ScreenEntity> fields = screenRedisService.livestockGroupBySuitable();
        // 统计所有的 存栏总数。
        Integer sum = fields.stream().mapToInt(ScreenEntity::getCountNum).sum();
        Map<String,ScreenEntity> map = fields.stream().collect(Collectors.toMap(ScreenEntity::getSuitable, Function.identity(),(key1,key2)->key2));

        List<ScreenEntity> list = new ArrayList<>();
        // 查询所有的猪类别。
        List<DictDto> dicts = tBaseDictService.tByDict("dict_type_1");
        for(int i=0;i<dicts.size();i++){
            DictDto r = dicts.get(i);
            ScreenEntity screen = new ScreenEntity();
            screen.setName(DictCache.getDict(r.getDictId()));
            Integer value = 0;
            String dictid = r.getDictId()+"";
            if(map.get(dictid) != null){
                value = map.get(dictid).getCountNum();
            }
            screen.setValue(value);
            screen.setPercent(df.format((float)value/sum*100)+"%");
            list.add(screen);
        }
        vo.setList(list);
        vo.setTotal(sum);
        return vo;
    }

    @Override
    public ScreenLineVo livestockGroupByMonth(ScreenEntity entity) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> list = new ArrayList<>();
        ScreenLine line = new ScreenLine();

        List<AnalysisVo> lives = analysisService.inNumByMonth(null);
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();

        List<String> months = monthOfYear();
        for(int i=0;i<months.size();i++){
            xAxisData.add(months.get(i));
            yAxisData.add(lives.get(i).getDictNum());
        }
        line.setName("存栏");
        line.setXAxisData(xAxisData);
        line.setYAxisData(yAxisData);
        list.add(line);

        line = new ScreenLine();
        xAxisData = new ArrayList<>();
        yAxisData = new ArrayList<>();
        List<ShedOutAnalysisVo> liveOuts = analysisService.outNumAnalysis(null);
        line.setName("出栏");
        for(int i=0;i<months.size();i++){
            xAxisData.add(months.get(i));
            yAxisData.add(liveOuts.get(i).getCountPig());
        }
        line.setXAxisData(xAxisData);
        line.setYAxisData(yAxisData);
        list.add(line);

        vo.setList(list);
        return vo;
    }

    private List<String> monthOfYear(){
        List<String> list = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        for(int i=TEST_NUM;i>=0;i--){
            LocalDate month = now.minusMonths(i);
            String m = dateTimeFormatter.format(month);
            list.add(m);
        }
        return list;
    }
    @Override
    public ScreenLineVo priceMonth(ScreenEntity entity) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> list = new ArrayList<>();

        ScreenLine line = new ScreenLine();
        List<TLivestockPrice> prices = priceMapper.tLivestockPriceList();

        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();

        for(int i= prices.size()-1;i>=0;i--) {
            xAxisData.add(prices.get(i).getPriceTime());
            yAxisData.add(prices.get(i).getPrice());
        }
        line.setXAxisData(xAxisData);
        line.setYAxisData(yAxisData);
        list.add(line);
        vo.setList(list);
        return vo;
    }

    @Override
    public IPage screenVaccineList(PageDomain page,ScreenEntity vo) {
        Page<ScreenEntity> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<ScreenVaccine> iPage = screenMapper.screenVaccineList(pg,vo);
        List<ScreenVaccine> list = iPage.getRecords();
        for(ScreenVaccine vaccine:list){
            vaccine.setCityName(DictCache.getAreaName(vaccine.getCity()));
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public ScreenVo provinceData(ScreenEntity entity) {
        ScreenVo vo = new ScreenVo();
        List<ScreenEntity> fields = screenRedisService.brdFieldGroupByCity();
        // 统计所有的养殖场总数。
        Map<String,ScreenEntity> map = fields.stream().collect(Collectors.toMap(ScreenEntity::getCity, Function.identity(),(key1,key2)->key2));

        List<ScreenEntity> livestocks = screenRedisService.livestockGroupByCity();
        List<ScreenEntity> sheouts = screenMapper.sheOutGroupByCity();
        Map<String,ScreenEntity> livestocksMap = livestocks.stream().collect(Collectors.toMap(ScreenEntity::getCity, Function.identity(),(key1,key2)->key2));
        Map<String,ScreenEntity> sheoutMap = sheouts.stream().collect(Collectors.toMap(ScreenEntity::getCity, Function.identity(),(key1,key2)->key2));
        List<ScreenEntity> list = new ArrayList<>();
        // 查询所有的城市。
        List<TBaseRegion> citys = iBsCityService.Citys();
        for(int i=0;i<citys.size();i++){
            TBaseRegion r = citys.get(i);
            ScreenEntity screen = new ScreenEntity();
            screen.setName(DictCache.getAreaName(r.getRegionCode()));
            Integer value = 0;
            if(map.get(r.getRegionCode()) != null){
                value = map.get(r.getRegionCode()).getCountNum();
            }
            screen.setFieldNum(value);
            Integer live = 0;
            Integer out = 0;
            if(livestocksMap.get(r.getRegionCode()) != null){
                live = livestocksMap.get(r.getRegionCode()).getCountNum();
            }
            if(sheoutMap.get(r.getRegionCode()) != null){
                out = sheoutMap.get(r.getRegionCode()).getCountNum();
            }
            screen.setLive(live);
            screen.setLiveOut(out);
            list.add(screen);
        }
        vo.setList(list);
        return vo;
    }


}
