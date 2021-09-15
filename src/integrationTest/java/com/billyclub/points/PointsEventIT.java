package com.billyclub.points;

import com.billyclub.points.model.PointsEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PointsEventIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPointsEvents_returnsAllPointEvents() throws Exception {
        ResponseEntity<PointsEvent[]> response = restTemplate.getForEntity("/api/v1/pointsEvent", PointsEvent[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getPointsEventById_returnsCorrectRecord() throws Exception {
        ResponseEntity<PointsEvent> response = restTemplate.getForEntity("/api/v1/pointsEvent/2",PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(2L);
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(3);
    }

    @Test
    public void savePointsEvent_returnsNewRecord() throws Exception {
        LocalDateTime ldt = LocalDateTime.now().withHour(6).withMinute(0).withSecond(0).withNano(0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ldt);
        pe.setNumOfTimes(7);

        HttpEntity<PointsEvent> request = new HttpEntity<>(pe);
        ResponseEntity<PointsEvent> response = restTemplate.postForEntity("/api/v1/pointsEvent", request, PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(7);

    }
    @Test
    public void updatePointsEvent_updatesCorrectRecord() throws Exception {
        LocalDateTime ldt = LocalDateTime.now().plusDays(7).withHour(6).withMinute(0).withSecond(0).withNano(0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ldt);
        pe.setNumOfTimes(2);
        pe.setId(1L);
        HttpEntity<PointsEvent> request = new HttpEntity<>(pe);

        ResponseEntity<PointsEvent> response = restTemplate.exchange("/api/v1/pointsEvent/1", HttpMethod.PUT, request, PointsEvent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getNumOfTimes()).isEqualTo(2);
    }


}
