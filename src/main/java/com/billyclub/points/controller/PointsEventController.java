package com.billyclub.points.controller;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PlayerModelAssembler;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.service.PointsEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
@RequestMapping("api/v1/pointsEvents")
public class PointsEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsEventController.class);
    @Autowired
    private final PointsEventService pointsEventService;
    @Autowired
    private final PointsEventModelAssembler eventAssembler;
    @Autowired
    private final PlayerModelAssembler playerAssembler;

    public PointsEventController(PointsEventService service,
                                 PointsEventModelAssembler eventAssembler,
                                 PlayerModelAssembler playerAssembler) {
        this.pointsEventService = service;
        this.eventAssembler = eventAssembler;
        this.playerAssembler = playerAssembler;
    }

    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntityModel<PointsEvent>> getById(@PathVariable Long id) throws PointsEventNotFoundException {
//    public ResponseEntity<PointsEvent> getById(@PathVariable Long id) throws PointsEventNotFoundException {
        final PointsEvent p = pointsEventService.findById(id);
        EntityModel<PointsEvent> model = eventAssembler.toModel(p);
        return new ResponseEntity<>(model,HttpStatus.OK);
//        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/{id}/players")
//    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Player>> getPlayers(@PathVariable Long eventId) throws PointsEventNotFoundException {
        final PointsEvent p = pointsEventService.findById(eventId);
        Set<EntityModel<Player>> players = p.getPlayers()
                .stream()
                .map(playerAssembler::toModel)
                .collect(Collectors.toSet());

        CollectionModel<EntityModel<Player>> collectionModel = CollectionModel.of(players);
        //        collectionModel.add(linkTo(methodOn(PointsEventController.class).getPlayers())).withSelfRel());
        return collectionModel;
    }

    @GetMapping
    public CollectionModel<EntityModel<PointsEvent>> getAll() {
        LOGGER.debug("enter controller.getAll()");
        List<EntityModel<PointsEvent>> listEntityModel = pointsEventService.getAll()
                .stream()
                .map(eventAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<PointsEvent>> collectionModel = CollectionModel.of(listEntityModel);
        collectionModel.add(linkTo(methodOn(PointsEventController.class).getAll()).withSelfRel());
        return collectionModel;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PointsEvent>> add(@RequestBody @Valid PointsEvent newEvent) {
        final PointsEvent p = pointsEventService.add(newEvent);
        EntityModel model = eventAssembler.toModel(p);
        return ResponseEntity.created(linkTo(methodOn(PointsEventController.class).add(p)).toUri()).body(model);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PointsEvent>> update(@PathVariable Long id, @Valid @RequestBody PointsEvent body) throws PointsEventNotFoundException {
        PointsEvent event = pointsEventService.findById(id);
        event.setEventDate(body.getEventDate());
        event.setStartTime(body.getStartTime());
        event.setNumOfTimes(body.getNumOfTimes());
        event.setPlayers(body.getPlayers());
        final PointsEvent updated = pointsEventService.save(event);
        EntityModel model = eventAssembler.toModel(updated);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<PointsEvent>> delete(@PathVariable Long id) throws PointsEventNotFoundException {
        PointsEvent toDelete = pointsEventService.findById(id);
        pointsEventService.delete(id);
        EntityModel<PointsEvent> model = eventAssembler.toModel(toDelete);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private void eventNotFoundHandler(PointsEventNotFoundException ex) {
//        LOGGER.debug("PointsEventNotFoundException "+ ex);
//        System.out.println("PointsEventNotFoundException "+ ex);
//    }
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ValidationError badRequestHandler(MethodArgumentNotValidException ex){
//        return createValidationError(ex);
//
//    }
//    private ValidationError createValidationError(MethodArgumentNotValidException ex) {
//        return ValidationErrorBuilder.fromBindingError(ex.getBindingResult());
//
//    }
}