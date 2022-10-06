package com.billyclub.points;

import com.billyclub.points.model.PointsEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PointsEventIT {

    @Autowired
    private TestRestTemplate restTemplate;
//    @LocalServerPort
//    int randomServerPort;
    @Test
    public void getPointsEvents_returnsAllPointEvents() throws Exception {
//        ResponseEntity<CollectionModel<PointsEvent>> response = restTemplate.getForObject("/api/v1/pointsEvents", CollectionModel.of(<EntityModel<PointsEvent>>).class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().length).isEqualTo(4);
    }


    @Test
    public void getPointsEventById_returnsCorrectRecord() throws Exception {
        ResponseEntity<PointsEvent> response = restTemplate.getForEntity("/api/v1/pointsEvent/1002",PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1002L);
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(4);
    }

    @Test
    public void savePointsEvent_returnsNewRecord() throws Exception {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.of(6, 30, 0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ld);
        pe.setStartTime(lt);
        pe.setNumOfTimes(7);

        HttpEntity<PointsEvent> request = new HttpEntity<>(pe);
        ResponseEntity<PointsEvent> response = restTemplate.postForEntity("/api/v1/pointsEvent", request, PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(7);
        assertThat(response.getBody().getEventDate()).isEqualTo(ld);
        assertThat(response.getBody().getStartTime()).isEqualTo(lt);

    }
    @Test
    public void updatePointsEvent_updatesCorrectRecord() throws Exception {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.of(6, 30, 0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ld);
        pe.setStartTime(lt);
        pe.setNumOfTimes(5);
        pe.setId(1000L);

        HttpEntity<PointsEvent> request = new HttpEntity<>(pe);

        ResponseEntity<PointsEvent> response = restTemplate.exchange("/api/v1/pointsEvent/1000", HttpMethod.PUT, request, PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1000L);
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(5);
        assertThat(response.getBody().getEventDate()).isEqualTo(ld);
        assertThat(response.getBody().getStartTime()).isEqualTo(lt);
    }

//    static class HandleResponse {
//        public static <T> T handleResponse(ResponseEntity response, Class<T> clazz){
//            HttpStatus status = response.getStatusCode();
//            if(status == HttpStatus.OK || status == HttpStatus.CREATED){
//                return (T) response.getBody();
//            }
//        }
//
//    }
}
