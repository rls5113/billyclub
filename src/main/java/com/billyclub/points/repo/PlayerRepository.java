package com.billyclub.points.repo;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
