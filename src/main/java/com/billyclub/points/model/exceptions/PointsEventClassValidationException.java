package com.billyclub.points.model.exceptions;

public class PointsEventClassValidationException extends RuntimeException {
    public PointsEventClassValidationException() {
        super("Not a valid PointsEvent.  Only valid data accepted.");
    }

}
