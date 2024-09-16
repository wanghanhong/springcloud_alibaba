package com.smart.card.common.dict.dict;

import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsCityMapper;
import com.smart.card.common.dict.entity.TBaseDict;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
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

    public static Map<String, TBaseDict> cache = new HashMap<>();
    public static Map<String, BsRegion> area = new HashMap<>();

    static {
        //注意此处不能使用依赖注入，因为此时dictionariesRepository还没创建出来，可以获取上下文手动创建
        tBaseDictMapper = SpringUtils.getBean(TBaseDictMapper.class);
        bsCityMapper = SpringUtils.getBean(BsCityMapper.class);
        toData();
        saveArea();
    }

    public static void saveArea(){
        List<BsRegion> dicts = bsCityMapper.queryCitysByCode("");
        dicts.forEach(e->{
            String key = "area_"+e.getRegionCode();
            area.put(key,e);
        });
    }
    public static String getAreaName(String key){
        BsRegion dict = new BsRegion();
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
            String key = e.getDictTypeId()+"_"+e.getDictId();
            cache.put(key,e);
        });
    }
    public static String getDict(String dictTypeId,String key){
        TBaseDict dict = new TBaseDict();
        if(StringUtils.isNotBlank(key)){
            dict = cache.get(dictTypeId+"_"+key);
            if(dict != null){
                return dict.getDictName();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String str = getDict("dict_type_218","225");
        System.out.println("11");
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
