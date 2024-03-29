package com.billyclub.points.controller.advice;

import com.billyclub.points.exceptions.ErrorDetails;
import com.billyclub.points.model.exceptions.PointsEventClassValidationException;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class PointsEventControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(PointsEventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> pointsEventNotFoundExceptionHandler(PointsEventNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String errMsg = error.getDefaultMessage();
            errors.add(errMsg);
        });

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),errors, "Points Event Validation failed." );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
