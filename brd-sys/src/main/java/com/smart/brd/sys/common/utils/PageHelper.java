package com.smart.brd.sys.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-04-16
 * Time: 19:47
 *
 * @author Pano
 */
public class PageHelper {

    /**
     * 获取参数中分页信息
     */
    public static <T> Page<T> getPage(Map<String, Object> params) {

        //如无参数，则为默认值
        if (Objects.isNull(params) || params.isEmpty()) {
            return new Page<>(1, 10);
        }
        int current = Integer.parseInt(String.valueOf(params.getOrDefault("current", 1)));
        int size = Integer.parseInt(String.valueOf(params.getOrDefault("size", 10)));
        return new Page<>(current, size);
    }
}
