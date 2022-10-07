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
    @NotNull(message = "")
    private String name;
    @NotNull(message = "Starting time cannot be null or empty")
    private Integer pointsToPull;

    private Integer pointsForEvent;

    private LocalDateTime timeEntered;

    //TODO:  add number of birdies made by player for this event
    //TODO:  add user object, and use to supply player name

    public Player(Long id) {
        this.id = id;
    }

//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
