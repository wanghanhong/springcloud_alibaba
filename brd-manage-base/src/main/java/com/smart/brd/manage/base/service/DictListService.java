package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TBrdField;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TBrdFieldMapper;
import com.smart.brd.manage.base.service.ITBrdFieldService;
import com.smart.brd.manage.base.service.area.ITBaseRegionService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictListService{
    /**
     * 逗号
     */
    private static final String COMMA = ",";
    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBrdFieldMapper tBrdFieldMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private ITBaseRegionService tBaseRegionService;

    /**------通用方法开始结束-----------------------------------------*/
    // 将list<1,2,3> 变为 1,2,3  添加和修改时使用
    public String typeListToType(List<Integer> types) {
        StringBuilder type = new StringBuilder();
        for (Integer t:types) {
            type.append(t);
            type.append(",");
        }
        if (type.lastIndexOf(COMMA) >= 0) {
            type.deleteCharAt(type.lastIndexOf(","));
        }
        return type.toString();
    }
    // 将1,2,3   变为  list<1,2,3>  修改时使用
    public List<Integer> typeToTypeList(String type){
        List<Integer> ty = new ArrayList<>();
        if(StringUtils.isNotBlank(type)){
            if (type.contains(COMMA)) {
                String[] t = type.split(",");
                for (String s:t) {
                    ty.add(Integer.parseInt(s));
                }
            }else {
                ty.add(Integer.parseInt(type));
            }
        }
        return ty;
    }
    // 将list<1,2,3> 变为 猪,牛,羊  添加和修改时使用
    public String typeListToName(String dictType,List<Integer> types){
        Map<Integer,String> map = getTypeMap(dictType);
        StringBuilder t = new StringBuilder();
        for (Integer type:types){
            t.append(map.get(type));
            t.append(",");
        }
        t.deleteCharAt(t.lastIndexOf(","));
        return t.toString();
    }

    private Map<Integer,String> getTypeMap(String dictType){
        List<TBaseDict> dicts = getType(dictType);
        Map<Integer, String> map = new HashMap<>();
        try {
            map = dicts.stream().collect(Collectors.toMap(TBaseDict::getDictId,TBaseDict::getDictName,(key1, key2)->key2));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    private List<TBaseDict> getType(String dictType){
        QueryWrapper<TBaseDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", dictType);
        return tBaseDictMapper.selectList(wrapper);
    }
    //根据dictId查询dictName
    public String selectNameById(String dictId) {
        return tBaseDictMapper.selectNameById(dictId);
    }
}
