package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseDict;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.install.mapper.TBaseDictMapper;
import com.smart.device.install.service.ITBaseDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author f
 */
@Service
public class TBaseDictServiceImpl extends ServiceImpl<TBaseDictMapper, TBaseDict> implements ITBaseDictService {

    @Resource
    public TBaseDictMapper tBaseDictMapper;

    @Override
    public List<TBaseDict> dictListByType(String type){
        List<TBaseDict> list = tBaseDictMapper.dictList(type);
        return list;
    }
    @Override
    public TBaseDict getdictName(String type,String dictid){
        TBaseDict  dict = tBaseDictMapper.getdictName(type,dictid);
        return dict;
    }

    @Override
    public Map<Integer,TBaseDict> dictListByTypeId(String dictTypeId){
        List<TBaseDict> list = tBaseDictMapper.dictList(dictTypeId);
        Map<Integer,TBaseDict> map = list.stream().collect(Collectors.toMap(TBaseDict::getDictId,Function.identity(),(key1,key2)-> key2));
        return map;
    }

}
