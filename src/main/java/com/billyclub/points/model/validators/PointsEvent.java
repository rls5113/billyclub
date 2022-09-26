package com.billyclub.points.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = PointsEventValidator_1.class)
@Documented
@interface PointsEvent {
    String message() default "Payload must conform to PointsEvent.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
