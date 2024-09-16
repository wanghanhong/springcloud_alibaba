package com.smart.brd.manage.base.common.dict;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典切面
 *
 * @author Tellsea
 * @date 2020/6/23
 */
@Aspect
@Component
@Slf4j
public class DictAspect {
    /**
     * 字典后缀
     */
    private static String DICT_TEXT_SUFFIX = "Name";
    /**
     * 时间 字符串
     */
    private static String LOCAL_DATE_TIME = "java.time.LocalDateTime";
    private static String LOCAL_DATE = "java.time.LocalDate";

    /**
     * 切点，切入 controller 包下面的所有方法
     *
     * com.smart.brd.manage.base.controller
     */
    @Pointcut("execution( * com.smart.brd.manage.base.controller.*.*(..))")
    public void dict() {
    }

    @Around("dict()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        parseDictText(result);
        return result;
    }

    private void parseDictText(Object result) {
        if (result instanceof Result) {

            Result rr = (Result) result;
            if (rr.getData() != null) {
                if (rr.getData() instanceof PageResult ) {
                    List<JSONObject> items = new ArrayList<>();
                    PageResult pageResult = (PageResult)rr.getData();
                    List<?> list = (List<?>) pageResult.getList();
                    for (Object record : list) {
                        JSONObject item = (JSONObject) JSON.toJSON(record);
                        dictConver(record, item);
                        items.add(item);
                    }
                    pageResult.setList(items);
                    rr.setData(pageResult);
                }else if (rr.getData() instanceof ArrayList
                        || rr.getData() instanceof List) {
                    List<JSONObject> items = new ArrayList<>();
                    List<?> list = (List<?>)rr.getData();
                    for (Object record : list) {
                        JSONObject item = (JSONObject) JSON.toJSON(record);
                        dictConver(record, item);
                        items.add(item);
                    }
                    rr.setData(items);
                }else if (rr.getData() instanceof Serializable) {
                    Object record = rr.getData();
                    JSONObject item = (JSONObject) JSON.toJSON(record);
                    dictConver(record, item);
                    rr.setData(item);
                }

            }
        }
    }

    private void dictConver(Object record, JSONObject item) {
        for (Field field : ObjConvertUtils.getAllFields(record)) {
            // 如果该属性上面有@Dict注解，则进行翻译
            if (field.getAnnotation(Dict.class) != null) {
                // 拿到注解的dictDataSource属性的值
//                String dictTypeId = field.getAnnotation(Dict.class).dictTypeId();
                // 拿到注解的dictText属性的值
                String text = field.getAnnotation(Dict.class).dictText();
                //获取当前带翻译的值
                String value = String.valueOf(item.get(field.getName()));
                //翻译字典值对应的text值
                String textValue = translateDictValue(value);
                if (!StringUtils.isBlank(text)) {
                    item.put(text, textValue);
                } else {
                    // 走默认策略
                    item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                }
            }
            if (item.get(field.getName()) != null) {
                timeSet(item, field);
            }
        }
    }

    private void timeSet(JSONObject item, Field field) {

            if (LOCAL_DATE_TIME.equals(field.getType().getName()) ) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String date = String.valueOf(item.get(field.getName()));
                    item.put(field.getName(),sdf2.format(sdf.parse(date)) );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if (LOCAL_DATE.equals(field.getType().getName()) ) {
                try {
                    String date = String.valueOf(item.get(field.getName()));
                    item.put(field.getName(),date);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }

    /**
     * 翻译字典文本
     *
     * @param
     * @return
     */
    private String translateDictValue(String value) {
        if (ObjConvertUtils.isEmpty(value)) {
            return null;
        }
        return DictCache.getDict(value);
    }
}

