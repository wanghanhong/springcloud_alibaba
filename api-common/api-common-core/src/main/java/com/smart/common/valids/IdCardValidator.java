package com.smart.common.valids;

import cn.hutool.core.util.IdcardUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 三多
 * @Time 2020/7/13
 */
public class IdCardValidator implements ConstraintValidator<IdCard,Object> {
    /**
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //身份证号，支持18位、15位和港澳台的10位
        return IdcardUtil.isValidCard(value.toString());
    }

    /**
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(IdCard constraintAnnotation) {

    }
}
