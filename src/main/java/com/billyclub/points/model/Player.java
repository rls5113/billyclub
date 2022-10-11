package com.billyclub.points.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
    @SequenceGenerator(name="player_id_seq", sequenceName = "player_seq", allocationSize = 1, initialValue = 1000)
    @Column(name="id",updatable = false, nullable = false)
    public Long id;
    @NotNull(message = "Name is required.")
    private String name;
    @NotNull(message = "Points to pull for this event is required.")
    private Integer pointsToPull;

    private Integer pointsThisEvent;

    @NotNull(message = "Time of entry for this player is required.")
    private LocalDateTime timeEntered;

    //TODO:  add number of birdies made by player for this event
    //TODO:  add user object, and use to supply player name

    public Player(String name, Integer pointsToPull, Integer pointsThisEvent, LocalDateTime timeEntered) {
        this.name = name;
        this.pointsToPull = pointsToPull;
        this.pointsThisEvent = pointsThisEvent;
        this.timeEntered = timeEntered;
    }


//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
