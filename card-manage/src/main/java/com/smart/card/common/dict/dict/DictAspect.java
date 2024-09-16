package com.smart.card.common.dict.dict;

import cn.hutool.json.JSONObject;
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
public class DictAspect{
    /**
     * 字典后缀
     */
    private static String DICT_TEXT_SUFFIX = "Dic";

    /**
     * 切点，切入 controller 包下面的所有方法
     *
     *
     */
    @Pointcut("execution( * com.smart.card.manage.controller.*.*(..))")
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
                        JSONObject item = new JSONObject(record);
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
                        JSONObject item = new JSONObject(record);
                        dictConver(record, item);
                        items.add(item);
                    }
                    rr.setData(items);
                }else if (rr.getData() instanceof Serializable) {
                    Object record = rr.getData();
                    JSONObject item = new JSONObject(record);
                    dictConver(record, item);
                    rr.setData(item);
                }

            }
        }
    }

    private void dictConver(Object record, JSONObject item) {
        for (Field field : ObjConvertUtils.getAllFields(record)) {
            // 如果该属性上面有@Dict注解，则进行翻译
            try {
                if (field.getAnnotation(Dict.class) != null) {

                    // 拿到注解的dictDataSource属性的值
//                String dictTypeId = field.getAnnotation(Dict.class).dictTypeId();
                    // 拿到注解的dictText属性的值
                    String text = field.getAnnotation(Dict.class).dictText();
                    String dict_type_id = field.getAnnotation(Dict.class).value();
                    //获取当前带翻译的值
                    String value = String.valueOf(item.get(field.getName()));
                    //翻译字典值对应的text值
                    String textValue = translateDictValue(dict_type_id,value);
                    if (!StringUtils.isBlank(text)) {
                        item.put(text, textValue);
                    } else {
                        // 走默认策略
                        item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                    }
                }
                if (item != null && item.containsKey(field.getName()) && item.get(field.getName()) != null) {
                    timeSet(item, field);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void timeSet(JSONObject item, Field field) {
            if ("java.time.LocalDateTime".equals(field.getType().getName()) ) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                    SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String date = String.valueOf(item.get(field.getName()));
                    if(date.length() > 16){
                        item.put(field.getName(),sdf5.format(sdf.parse(date)) );
                    }else{
                        item.put(field.getName(),sdf5.format(sdf2.parse(date)) );
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if ("java.time.LocalDate".equals(field.getType().getName()) ) {
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
    private String translateDictValue(String dictTypeId,String value) {
        if (ObjConvertUtils.isEmpty(value)) {
            return null;
        }
        return DictCache.getDict(dictTypeId,value);
    }
}

