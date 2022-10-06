package com.billyclub.points.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PointsEventNotFoundException extends RuntimeException {
    public PointsEventNotFoundException(String message) {
        super(message);
    }

}
