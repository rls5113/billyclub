package com.billyclub.points;

import com.billyclub.points.model.PointsEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPointsEvents_returnsAllPointEvents() throws Exception {

        ResponseEntity<PointsEvent[]> response =  restTemplate.getForEntity("/pointsEvent", PointsEvent[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Movie[] results = response.getBody();
//        assertThat(results.length).isGreaterThanOrEqualTo(72);
    }
}
