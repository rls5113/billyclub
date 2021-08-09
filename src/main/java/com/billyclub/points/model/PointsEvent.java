package com.billyclub.points.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PointsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointsevent_id_seq")
    @SequenceGenerator(name="pointsevent_id_seq", sequenceName = "pointsevent_seq", allocationSize = 1)
    @Column(name="id",updatable = false, nullable = false)
    private long id;
    private LocalDateTime eventDate;
    private int numOfTimes;
    public PointsEvent(LocalDateTime ldt, int numOfTimes) {
        this.eventDate = ldt;
        this.numOfTimes = numOfTimes;
    }
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
