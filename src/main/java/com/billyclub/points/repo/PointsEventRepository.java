package com.billyclub.points.repo;

import com.billyclub.points.model.PointsEvent;
import org.springframework.data.repository.CrudRepository;

public interface PointsEventRepository extends CrudRepository<PointsEvent, Long> {
    // public PointsEvent findByEventDate(LocalDateTime date);
}
