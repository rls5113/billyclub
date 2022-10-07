package com.billyclub.points.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Player {//extends RepresentationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointsevent_id_seq")
    @SequenceGenerator(name="pointsevent_id_seq", sequenceName = "pointsevent_seq", allocationSize = 1, initialValue = 1000)
    @Column(name="id",updatable = false, nullable = false)
    public Long id;
    @NotNull(message = "Event date cannot be null or empty")
    private LocalDate eventDate;
    @NotNull(message = "Starting time cannot be null or empty")
    private LocalTime startTime;
    @NotNull(message = "Number of tee times cannot be null or empty")
    private Integer numOfTimes;
    public Player(LocalDate date, LocalTime time, int numOfTimes) {
        this.eventDate = date;
        this.startTime = time;
        this.numOfTimes = numOfTimes;
    }
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
