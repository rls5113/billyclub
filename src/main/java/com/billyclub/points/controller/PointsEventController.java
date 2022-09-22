package com.billyclub.points.controller;

import com.billyclub.points.exceptions.ResourceNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.service.PointsEventService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/pointsEvents/{id}")
    private ResponseEntity<PointsEvent> getPointsEventById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<PointsEvent>(pointsEventService.getPointsEventById(id), HttpStatus.OK);
    }

    @GetMapping("/pointsEvents")
    private ResponseEntity<List<PointsEvent>> getAllPointsEvents() {
        return new ResponseEntity<>(pointsEventService.getAllPointsEvents(), HttpStatus.OK);
    }

    @PostMapping("/pointsEvents")
    private ResponseEntity<PointsEvent> savePointsEvent(@RequestBody PointsEvent newPointsEvent) {
        return new ResponseEntity<>(pointsEventService.savePointsEvent(newPointsEvent), HttpStatus.CREATED);
    }
    @PutMapping("/pointsEvents/{id}")
    private ResponseEntity<PointsEvent> updatePointsEvent(@PathVariable Long id,@RequestBody PointsEvent body) throws ResourceNotFoundException {
        PointsEvent event = pointsEventService.getPointsEventById(id);
        if(event == null) throw new ResourceNotFoundException("PointsEvent[ id:"+id+" ]");

        event.setEventDate(body.getEventDate());
        event.setNumOfTimes(body.getNumOfTimes());
        final PointsEvent updatedEvent = pointsEventService.savePointsEvent(event);

        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/pointsEvents/{id}")
    private ResponseEntity<?> deletePointsEventById(@PathVariable Long id) throws ResourceNotFoundException {
        pointsEventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void eventNotFoundHandler(ResourceNotFoundException ex) {

    }
}
