package com.billyclub.points.data;

import com.billyclub.points.model.PointsEvent;
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

@Configuration
//@Profile("dev")
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PointsEventRepository repository) {
        System.out.println("loading data");
        return args -> {
            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(7),2)));
            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(8),3)));
            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(14),4)));
            log.info("Preloading " + repository.save(new PointsEvent(LocalDate.now().plusDays(15),3)));
        };
    }
}