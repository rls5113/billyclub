package com.billyclub.points.service;

import com.billyclub.points.exceptions.ResourceNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.exceptions.PointsEventClassValidationException;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.repo.PointsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PointsEventService {
    @Autowired
    PointsEventRepository pointsEventRepo;

    public PointsEventService(PointsEventRepository pointsEventRepo) {

        this.pointsEventRepo = pointsEventRepo;
    }

    public List<PointsEvent> getAll() {

        return (List<PointsEvent>) pointsEventRepo.findAll();
    }

    public PointsEvent save(PointsEvent newPointsEvent) {
        return pointsEventRepo.save(newPointsEvent);
    }

    public PointsEvent add(PointsEvent newPointsEvent) {
        return pointsEventRepo.save(newPointsEvent);
    }

    public PointsEvent findById(Long id) {
        PointsEvent event = pointsEventRepo.findById(id)
                .orElseThrow(() -> new PointsEventNotFoundException("Cannot find PointsEvent for id = "+id));

        return  event;
    }
    //delete
    public void delete(Long id) {
        pointsEventRepo.deleteById(id);
    }

}
