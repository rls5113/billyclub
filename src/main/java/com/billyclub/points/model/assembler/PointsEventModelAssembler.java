package com.billyclub.points.model.assembler;

import com.billyclub.points.controller.PointsEventController;
import com.billyclub.points.model.PointsEvent;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class PointsEventModelAssembler implements RepresentationModelAssembler<PointsEvent, EntityModel<PointsEvent>> {

    @Override
    public EntityModel<PointsEvent> toModel(PointsEvent event) {

        EntityModel<PointsEvent> eventModel = EntityModel.of(event);
        eventModel.add(linkTo(methodOn(PointsEventController.class).getById(event.getId())).withSelfRel());
        eventModel.add(linkTo(methodOn(PointsEventController.class).getAll()).withRel("events"));
        return eventModel;
    }

    public CollectionModel<EntityModel<PointsEvent>> toCollectionModel(Iterable<? extends PointsEvent> events) {

        return RepresentationModelAssembler.super.toCollectionModel(events);
    }

}
