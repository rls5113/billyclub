package com.billyclub.points.controller;

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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
    public void addNewPointsEvent_shouldCreateAndReturnEvent() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(567l,ldt ,6);
        given(pointsEventService.savePointsEvent(pe)).willReturn(pe);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pointsEvent"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
    }

    @Test
    public void getAllEvents_shouldReturnAllEvent() throws Exception {
        List<PointsEvent> events = new ArrayList<>();
        for(int i=0;i<3;i++) {
            LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,i+2,0,0);
            PointsEvent pe = new PointsEvent(i, ldt ,i+1);
            events.add(pe);
        }
        given(pointsEventService.getAllPointsEvents()).willReturn(events);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numOfTimes").value("1"))
                .andExpect(jsonPath("$[1].numOfTimes").value("2"))
                .andExpect(jsonPath("$[2].numOfTimes").value("3"))
        ;
    }


}
