package com.billyclub.points.repo;

import com.billyclub.points.model.PointsEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointsEventRepository extends CrudRepository<PointsEvent, Long> {
     public Optional<List<PointsEvent>> findByEventDate(LocalDate eventDate);
}
