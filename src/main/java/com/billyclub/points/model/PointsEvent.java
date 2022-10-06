package com.billyclub.points.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PointsEvent  {//extends RepresentationModel {
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
