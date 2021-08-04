package com.billyclub.points.controller;

import com.billyclub.points.exceptions.PointsEventNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.service.PointsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointsEventController {

    @Autowired
    PointsEventService pointsEventService;

    @GetMapping("/api/v1/pointsEvent")
    private List<PointsEvent> getPointsEvent() {

        return pointsEventService.getAllPointsEvents();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void eventNotFoundHandler(PointsEventNotFoundException ex) {

    }
}
