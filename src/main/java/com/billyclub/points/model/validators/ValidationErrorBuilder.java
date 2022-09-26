package com.billyclub.points.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {
    public static ValidationError fromBindingError(Errors errors) {
        ValidationError err = new ValidationError("Validation failed. "+
                errors.getErrorCount()+" errors(s)");
        for(ObjectError objErr: errors.getAllErrors()) {
            err.addValidationError(objErr.getDefaultMessage());
        }
        return err;
    }
}
