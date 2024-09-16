package com.smart.card.common.dict.dict;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDictType;
import com.smart.card.common.dict.service.ITBaseDictService;
import com.smart.card.common.dict.service.ITBaseDictTypeService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 
 */
@Service
public class DictRedisService{

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ITBaseDictService itBaseDictService;
    @Resource
    private ITBaseDictTypeService itBaseDictTypeService;

    public void DictToRedis(){
        List<TBaseDictType> list = itBaseDictTypeService.findAll();
        try {
            for(TBaseDictType type:list){
                List<DictDto> dicts = itBaseDictService.tByDict(type.getDictTypeId());
                redisTemplate.opsForValue().set(type.getDictTypeId(),dicts);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
