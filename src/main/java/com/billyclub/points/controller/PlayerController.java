package com.billyclub.points.controller;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PlayerModelAssembler;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.model.exceptions.PlayerNotFoundException;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.service.PlayerService;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
@RequestMapping("api/v1/players")
public class PlayerController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private final PlayerService service;
    @Autowired
    private final PlayerModelAssembler assembler;
    public PlayerController(PlayerService service, PlayerModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntityModel<Player>> getById(@PathVariable Long id) throws PlayerNotFoundException {
        final Player p = service.findById(id);
        EntityModel<Player> model = assembler.toModel(p);
        return new ResponseEntity<>(model,HttpStatus.OK);
    }

    @GetMapping
    public CollectionModel<EntityModel<Player>> getAll() {
        LOGGER.debug("enter controller.getAll()");
        List<EntityModel<Player>> listEntityModel = service.getAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Player>> collectionModel = CollectionModel.of(listEntityModel);
        collectionModel.add(linkTo(methodOn(PlayerController.class).getAll()).withSelfRel());
        return collectionModel;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Player>> add(@RequestBody @Valid Player newEvent) {
        final Player p = service.add(newEvent);
        EntityModel model = assembler.toModel(p);
        return ResponseEntity.created(linkTo(methodOn(PlayerController.class).add(p)).toUri()).body(model);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Player>> update(@PathVariable Long id, @Valid @RequestBody Player body) throws PlayerNotFoundException {
        Player player = service.findById(id);
        player.setName(body.getName());
        player.setPointsToPull(body.getPointsToPull());
        player.setPointsThisEvent(body.getPointsThisEvent());
        player.setTimeEntered(body.getTimeEntered());
        final Player updated = service.save(player);
        EntityModel model = assembler.toModel(updated);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Player>> delete(@PathVariable Long id) throws PlayerNotFoundException {
        Player toDelete = service.findById(id);
        service.delete(id);
        EntityModel<Player> model = assembler.toModel(toDelete);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}