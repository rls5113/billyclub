package com.billyclub.points.service;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.exceptions.PlayerNotFoundException;
import com.billyclub.points.model.exceptions.PointsEventNotFoundException;
import com.billyclub.points.repo.PlayerRepository;
import com.billyclub.points.repo.PointsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {

        this.repo = repo;
    }

    public List<Player> getAll() {

        return (List<Player>) repo.findAll();
    }

    public Player save(Player player) {
        return repo.save(player);
    }

    public Player add(Player player) {
        return repo.save(player);
    }

    public Player findById(Long id) {
        Player player = repo.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Cannot find Player for id = "+id));

        return  player;
    }
    //delete
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
