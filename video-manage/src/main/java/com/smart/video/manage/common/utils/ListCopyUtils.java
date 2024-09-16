package com.smart.video.manage.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.common.utils.bean.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

public class ListCopyUtils extends BeanUtils{

    public static <T> List copyList(List<T> list,Class clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(list);
            List<T> t = mapper.readValue(result,new TypeReference<List<T>>(){});
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }



}
