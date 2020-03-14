package com.example.mybatis.demomybatis.utils;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 *
 * @Author jacksparrow414
 * @Date 2019-12-23
 * @Description: TODO
 */
public class ValidatorUtils {

    private static Validator validator;
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void validate(Object object,Class<?>... groups){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object,groups);
        if (!CollectionUtils.isEmpty(constraintViolations)){
            ConstraintViolation<Object> violation = constraintViolations.iterator().next();
            violation.getMessage();
        }
    }
}
