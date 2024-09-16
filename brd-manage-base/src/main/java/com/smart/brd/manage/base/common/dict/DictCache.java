package com.smart.brd.manage.base.common.dict;

import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.area.BsCity;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.area.BsCityMapper;
import com.smart.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DictCache {

    private static TBaseDictMapper tBaseDictMapper;

    private static BsCityMapper bsCityMapper;

    public static Map<String,TBaseDict> cache = new HashMap<>();
    public static Map<String,TBaseRegion> area = new HashMap<>();
    private static String s = "dict_";

    static {
        //注意此处不能使用依赖注入，因为此时dictionariesRepository还没创建出来，可以获取上下文手动创建
        tBaseDictMapper = SpringUtils.getBean(TBaseDictMapper.class);
        bsCityMapper = SpringUtils.getBean(BsCityMapper.class);
        toData();
        saveArea();
    }

    public static void saveArea(){
        List<TBaseRegion> dicts = bsCityMapper.queryCitysByCode("");
        dicts.forEach(e->{
            String key = "area_"+e.getRegionCode();
            area.put(key,e);
        });
    }
    public static String getAreaName(String key){
        TBaseRegion dict = new TBaseRegion();
        if(StringUtils.isNotBlank(key)){
            dict = area.get("area_"+key);
            if(dict != null){
                return dict.getRegionName();
            }
        }
        return "";
    }

    public static void toData(){
        List<TBaseDict> dicts = tBaseDictMapper.findAll();
        dicts.forEach(e->{
            String key = s +e.getDictId();
            cache.put(key,e);
        });
    }
    public static String getDict(String key){
        TBaseDict dict = new TBaseDict();
        if(StringUtils.isNotBlank(key)){
            dict = cache.get(s +key);
            if(dict != null){
                return dict.getDictName();
            }
        }
        return "";
    }
    public static String getDict(Integer key){
        TBaseDict dict = new TBaseDict();
        if(key != null){
            dict = cache.get(s +key);
            if(dict != null){
                return dict.getDictName();
            }
        }
        return "";
    }
    public static void main(String[] args) {
        String str = getDict(2);
        log.info("11");
    }


//    /** 返回list数据格式 */
//    public static List<TBaseDict> getDictList(String key){
//        List<TBaseDict> dict = cache.get(key);
//        if(CollectionUtils.isEmpty(dictionariesList)){
//            return new ArrayList<>();
//        }
//        return cache.get(findType);
//    }

}
