package com.billyclub.points.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="event_player",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"))
    private Set<Player> players = new HashSet<>();
    public PointsEvent(LocalDate date, LocalTime time, int numOfTimes) {
        this.eventDate = date;
        this.startTime = time;
        this.numOfTimes = numOfTimes;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    //    public void addPlayer(Player player) {
//        this.getPlayers().add(player);
//    }
//    public void removePlayer(Player player) {
//        this.getPlayers().remove(player);
//    }
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
}
