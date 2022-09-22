package com.billyclub.points.controller;

import com.billyclub.points.exceptions.ResourceNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.service.PointsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PointsEventController {

    @Autowired
    private final PointsEventService pointsEventService;
    @Autowired
    private final PointsEventModelAssembler assembler;
    public PointsEventController(PointsEventService service, PointsEventModelAssembler assembler) {
        this.pointsEventService = service;
        this.assembler = assembler;
    }

    @GetMapping("/pointsEvents/{id}")
    public EntityModel<PointsEvent> getById(@PathVariable Long id) throws ResourceNotFoundException {
        PointsEvent p = pointsEventService.findById(id);
        return assembler.toModel(p);
    }

    @GetMapping("/pointsEvents")
    public ResponseEntity<List<PointsEvent>> getAll() {
        return new ResponseEntity<>(pointsEventService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/pointsEvents")
    public ResponseEntity<PointsEvent> add(@RequestBody PointsEvent newPointsEvent) {
        return new ResponseEntity<>(pointsEventService.save(newPointsEvent), HttpStatus.CREATED);
    }
    @PutMapping("/pointsEvents/{id}")
    public ResponseEntity<PointsEvent> update(@PathVariable Long id,@RequestBody PointsEvent body) throws ResourceNotFoundException {
        PointsEvent event = pointsEventService.findById(id);
        event.setEventDate(body.getEventDate());
        event.setStartTime(body.getStartTime());
        event.setNumOfTimes(body.getNumOfTimes());
        final PointsEvent updatedEvent = pointsEventService.save(event);

        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/pointsEvents/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws PointsEventNotFoundException {
        pointsEventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private void eventNotFoundHandler(PointsEventNotFoundException ex) {
//
//    }
}
