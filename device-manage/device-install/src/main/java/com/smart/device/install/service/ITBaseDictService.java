package com.smart.device.install.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseDict;

import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface ITBaseDictService extends IService<TBaseDict> {

     List<TBaseDict> dictListByType(String type);
     TBaseDict getdictName(String type,String dictid);

     Map<Integer,TBaseDict> dictListByTypeId(String dictTypeId);
}
