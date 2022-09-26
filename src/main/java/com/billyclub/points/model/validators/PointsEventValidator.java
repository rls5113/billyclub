package com.billyclub.points.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PointsEventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PointsEvent.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors,"eventDate","eventDate.empty");
        ValidationUtils.rejectIfEmpty(errors,"startTime","startTime.empty");
        ValidationUtils.rejectIfEmpty(errors,"numOfTimes","numOfTimes.empty");
        PointsEvent p = (PointsEvent) target;

    }
}
