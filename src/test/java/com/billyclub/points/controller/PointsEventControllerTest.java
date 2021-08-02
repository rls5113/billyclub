package com.billyclub.points.controller;

import com.billyclub.points.exceptions.PointsEventNotFoundException;
import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.service.PointsEventService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(PointsEventController.class)
public class PointsEventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PointsEventService pointsEventService;

    @Test
    public void getEvent_shouldReturnEvent() throws Exception {
        PointsEvent pointsEvent = new PointsEvent(1, LocalDateTime.now(),3);
        given(pointsEventService.getEventDetails(anyString())).willReturn(pointsEvent);

        mockMvc.perform(MockMvcRequestBuilders.get("/pointsEvents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("numberOfTeeTimes").value("3"));
    }

    @Test
    public void getEvent_notFound() throws Exception {
        given(pointsEventService.getEventDetails(anyString())).willThrow(new PointsEventNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/pointsEvents"))
                .andExpect(status().isNotFound());
    }


}
