package com.billyclub.points.model.assembler;

import com.billyclub.points.controller.PointsEventController;
import com.billyclub.points.model.PointsEvent;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class PointsEventModelAssembler implements RepresentationModelAssembler<PointsEvent, EntityModel<PointsEvent>> {

    @Override
    public EntityModel<PointsEvent> toModel(PointsEvent event) {
        return EntityModel.of(event,
                linkTo(methodOn(PointsEventController.class).getById(event.getId())).withSelfRel(),
                linkTo(methodOn(PointsEventController.class).getAll()).withRel("events")
        );
    }


}
