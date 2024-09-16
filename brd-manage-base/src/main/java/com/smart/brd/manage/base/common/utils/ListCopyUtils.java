package com.smart.brd.manage.base.common.utils;

import com.alibaba.fastjson.JSON;
import com.smart.common.utils.bean.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ListCopyUtils extends BeanUtils{

    public static <T> List copyList(List<T> list,Class clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list),clazz.getClass());
    }



}
