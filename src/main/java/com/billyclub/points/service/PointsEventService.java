package com.billyclub.points.service;

import com.billyclub.points.exceptions.PointsEventNotFoundException;
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

//    public PointsEvent getEventDetails(String date) {
//        //convert string to date
//        int yr = Integer.getInteger(date.substring(6, date.length()));
//        int mo = Integer.getInteger(date.substring(0, 2));
//        int day = Integer.getInteger(date.substring(3, 5));
//        PointsEvent event = pointsEventRepo.findByEventDate(LocalDateTime.of(yr,mo,day,0,0));
//        if(event == null) {
//            throw new PointsEventNotFoundException();
//        }
//        return event;
//    }
    public List<PointsEvent> getAllPointsEvents() {
        return (List<PointsEvent>) pointsEventRepo.findAll();
    }
    public PointsEvent savePointsEvent(PointsEvent newPointsEvent) {
        System.out.println(newPointsEvent.toString());
        return pointsEventRepo.save(newPointsEvent);
    }

    public PointsEvent getPointsEventById(Long id) {
        return  pointsEventRepo.findById(id).get();
    }


}
