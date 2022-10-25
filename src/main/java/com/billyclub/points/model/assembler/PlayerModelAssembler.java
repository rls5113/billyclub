package com.billyclub.points.model.assembler;

import com.billyclub.points.controller.PlayerController;
import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlayerModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {

    @Override
    public EntityModel<Player> toModel(Player object) {

        EntityModel<Player> eventModel = EntityModel.of(object);
        eventModel.add(linkTo(methodOn(PlayerController.class).getById(object.getId())).withSelfRel());
        eventModel.add(linkTo(methodOn(PlayerController.class).getAll()).withRel("players"));
        return eventModel;
    }
    @Override
    public CollectionModel<EntityModel<Player>> toCollectionModel(Iterable<? extends Player> players) {

        return RepresentationModelAssembler.super.toCollectionModel(players);
    }

}
