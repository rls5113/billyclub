package com.billyclub.points.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.billyclub.points.exceptions.ResourceNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.service.PointsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public CollectionModel<EntityModel<PointsEvent>> getAll() {
        List<EntityModel<PointsEvent>> events = pointsEventService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(events,
                linkTo(methodOn(PointsEventController.class).getAll()).withSelfRel());
    }
//    public ResponseEntity<List<PointsEvent>> getAll() {
//        return new ResponseEntity<>(pointsEventService.getAll(), HttpStatus.OK);
//    }

    @PostMapping(value = "/pointsEvents",
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} )
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<?> add(@Valid @RequestBody PointsEvent newEvent) {
        return assembler
                .toModel(pointsEventService.save(newEvent));
    }
    @PutMapping("/pointsEvents/{id}")
    public EntityModel<?> update(@PathVariable Long id, @RequestBody PointsEvent body) throws PointsEventNotFoundException {
        PointsEvent event = pointsEventService.findById(id);
        event.setEventDate(body.getEventDate());
        event.setStartTime(body.getStartTime());
        event.setNumOfTimes(body.getNumOfTimes());
        final PointsEvent updated = pointsEventService.save(event);
        return assembler.toModel(updated);
    }

    @DeleteMapping("/pointsEvents/{id}")
    public EntityModel<?> delete(@PathVariable Long id) throws PointsEventNotFoundException {
        PointsEvent toDelete = pointsEventService.findById(id);
        pointsEventService.delete(id);
        return assembler.toModel(toDelete);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void eventNotFoundHandler(PointsEventNotFoundException ex) {

    }
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private void eventNotFoundHandler(PointsEventNotFoundException ex) {
//
//    }
}
