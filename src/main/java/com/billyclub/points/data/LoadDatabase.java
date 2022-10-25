package com.billyclub.points.data;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.repo.PlayerRepository;
import com.billyclub.points.repo.PointsEventRepository;
//import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
@Profile("dev")
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//    @Bean
//    CommandLineRunner initEventsRecords(PointsEventRepository repository) {
//        System.out.println("loading data: POINTS EVENTS");
//        return args -> {
//            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(7), LocalTime.of(6,30,0) ,2)));
//            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(8), LocalTime.of(6,30,0) ,3)));
//            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(14), LocalTime.of(6,30,0) ,4)));
//            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(15), LocalTime.of(6,30,0) ,3)));
//        };
//    }
//    @Bean
//    CommandLineRunner initPlayerRecords(PlayerRepository repository) {
//        System.out.println("loading data: PLAYER");
//        return args -> {
//            log.info("Preloading " + repository.save(new Player("Herman Munster", 25,27,LocalDateTime.now())));
//            log.info("Preloading " + repository.save(new Player("Lilly Munster", 20,18,LocalDateTime.now())));
//            log.info("Preloading " + repository.save(new Player("Eddie Munster", 15,18,LocalDateTime.now())));
//        };
//    }
    @Bean
    CommandLineRunner initAddPlayersToEventRecords(PlayerRepository playerRepository, PointsEventRepository eventRepository) {
        System.out.println("loading data: PLAYER");
        return args -> {

            PointsEvent event1 = eventRepository.save(new PointsEvent(LocalDate.now().plusDays(7), LocalTime.of(6,30,0) ,2));
            PointsEvent event2 = eventRepository.save(new PointsEvent(LocalDate.now().plusDays(8), LocalTime.of(6,30,0) ,3));
            PointsEvent event3 = eventRepository.save(new PointsEvent(LocalDate.now().plusDays(14), LocalTime.of(6,30,0) ,4));
            PointsEvent event4 = eventRepository.save(new PointsEvent(LocalDate.now().plusDays(15), LocalTime.of(6,30,0) ,3));
            log.info("Preloading " + event1 );
            log.info("Preloading " + event2 );
            log.info("Preloading " + event3 );
            log.info("Preloading " + event4 );
            Player player1 = playerRepository.save(new Player("Herman Munster", 25,27,LocalDateTime.now()));
            Player player2 = playerRepository.save(new Player("Lilly Munster", 20,18,LocalDateTime.now()));
            Player player3 = playerRepository.save(new Player("Eddie Munster", 15,18,LocalDateTime.now()));
            log.info("Preloading " + player1);
            log.info("Preloading " + player2);
            log.info("Preloading " + player3);

            event1.getPlayers().add(player1);
            event1.getPlayers().add(player2);
//            player1.getEvents().add(event1);
//            player2.getEvents().add(event1);

            log.info("Saving event after adding players  "+ eventRepository.save(event1));
//            log.info("Saving event after adding players  "+ playerRepository.save(player1));
//            log.info("Saving event after adding players  "+ playerRepository.save(player2));

        };
    }
}