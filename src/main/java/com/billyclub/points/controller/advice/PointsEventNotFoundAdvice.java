package com.billyclub.points.controller.advice;

import com.billyclub.points.model.exceptions.PointsEventClassValidationException;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PointsEventNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PointsEventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pointsEventNotFoundHandler(PointsEventNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(PointsEventClassValidationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pointsEventBadRequestHandler(PointsEventClassValidationException ex) {
        return ex.getMessage();
    }
}
