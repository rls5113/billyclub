package com.billyclub.points.controller;

import com.billyclub.points.exceptions.PointsEventNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.service.PointsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PointsEventController {

    @Autowired
    private final PointsEventService pointsEventService;

    public PointsEventController(PointsEventService pointsEventService) {
        this.pointsEventService = pointsEventService;
    }

    @GetMapping("/pointsEvent/{id}")
    private ResponseEntity<PointsEvent> getPointsEventById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<PointsEvent>(pointsEventService.getPointsEventById(id), HttpStatus.OK);
    }

    @GetMapping("/pointsEvent")
    private ResponseEntity<List<PointsEvent>> getAllPointsEvents() {
        return new ResponseEntity<>(pointsEventService.getAllPointsEvents(), HttpStatus.OK);
    }

    @PostMapping(/pointsEvent)
    private ResponseEntity<PointsEvent> savePointsEvent(@RequestBody PointsEvent newPointsEvent) {
        return new ResponseEntity<>(pointsEventService.savePointsEvent(newPointsEvent), HttpStatus.CREATED);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void eventNotFoundHandler(PointsEventNotFoundException ex) {

    }
}
