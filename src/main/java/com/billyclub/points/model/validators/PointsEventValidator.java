package com.billyclub.points.model.validators;

import com.billyclub.points.model.PointsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component("beforeCreatePointsEventValidator")
public class PointsEventValidator implements Validator {
    private static final Logger LOG = LoggerFactory.getLogger(PointsEventValidator.class);
    public PointsEventValidator beforeCreatePointsEventValidator() {
        return new PointsEventValidator();
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return ClassUtils.isAssignable(clazz, PointsEvent.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        PointsEvent p = (PointsEvent) obj;
        LOG.debug("Validating PointsEvent "+p);
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"eventDate","field.eventDate.required","Field 'eventDate' cannot be empty.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"startTime","field.startTime.required","Field 'startTime' cannot be empty.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"numOfTimes","field.numOfTimes.required","Field 'numOfTimes' cannot be empty.");


    }
}
