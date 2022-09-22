package com.billyclub.points.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PointsEvent extends RepresentationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointsevent_id_seq")
    @SequenceGenerator(name="pointsevent_id_seq", sequenceName = "pointsevent_seq", allocationSize = 1, initialValue = 1000)
    @Column(name="id",updatable = false, nullable = false)
    private long id;
    private LocalDate eventDate;
    private LocalTime startTime;
    private int numOfTimes;
    public PointsEvent(LocalDate date, LocalTime time, int numOfTimes) {
        this.eventDate = date;
        this.startTime = time;
        this.numOfTimes = numOfTimes;
    }
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
