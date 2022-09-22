package com.billyclub.points.model.exceptions;

public class PointsEventNotFoundException extends RuntimeException {
    public PointsEventNotFoundException(Long id) {
        super("Could not find points event: "+id);
    }

}
