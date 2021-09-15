package com.billyclub.points.service;

import com.billyclub.points.exceptions.ResourceNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.repo.PointsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointsEventService {
    @Autowired
    PointsEventRepository pointsEventRepo;

    public PointsEventService(PointsEventRepository pointsEventRepo) {

        this.pointsEventRepo = pointsEventRepo;
    }

    public List<PointsEvent> getAllPointsEvents() {

        return (List<PointsEvent>) pointsEventRepo.findAll();
    }

    public PointsEvent savePointsEvent(PointsEvent newPointsEvent) {
        return pointsEventRepo.save(newPointsEvent);
    }

    public PointsEvent getPointsEventById(Long id) {
        PointsEvent event = pointsEventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PointsEvent not exists with id: "+id));

        return  event;
    }
    //delete
    public void delete(Long id) {
        pointsEventRepo.deleteById(id);
    }

}
