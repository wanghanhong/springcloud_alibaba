package com.smart.brd.manage.base.common.dict;

import com.smart.brd.manage.base.entity.TBaseDictType;
import com.smart.brd.manage.base.entity.dto.DictDto;
import com.smart.brd.manage.base.service.ITBaseDictService;
import com.smart.brd.manage.base.service.ITBaseDictTypeService;
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
