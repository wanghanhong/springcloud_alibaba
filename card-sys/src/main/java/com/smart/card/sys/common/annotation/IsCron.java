package com.smart.card.sys.common.annotation;

import com.smart.card.sys.common.validator.CronValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CronValidator.class)
public @interface IsCron {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
