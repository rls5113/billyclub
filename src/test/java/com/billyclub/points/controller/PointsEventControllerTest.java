package com.billyclub.points.controller;

import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.service.PointsEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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

    //get by id
    @Test
    public void getById_shouldReturnOneEvent() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000l, ldt, 6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        System.out.println(mapper.writeValueAsString(pe));
        when(pointsEventService.getPointsEventById(any())).thenReturn(pe);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvent/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pe))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
    }

    //update
    @Test
    public void updatePointsEvent_shouldChangeEventValues() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000l,ldt,2);

        LocalDateTime ldt1 = LocalDateTime.of(2021, Month.APRIL,8,0,0);
        PointsEvent pe1 = new PointsEvent(1000l,ldt1,6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        when(pointsEventService.getPointsEventById(any())).thenReturn(pe);
        when(pointsEventService.savePointsEvent(any(PointsEvent.class))).thenReturn(pe1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/pointsEvent/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pe1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
    }
    //delete
    @Test
    public void deletePointsEvent_shouldRemoveEventRecord() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pointsEvent/1000"))
                .andExpect(status().isOk())
        ;
    }

    //post
    @Test
    public void addNewPointsEvent_shouldCreateAndReturnEvent() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ldt);
        pe.setNumOfTimes(6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

//        System.out.println(mapper.writeValueAsString(pe));
        when(pointsEventService.savePointsEvent(any(PointsEvent.class))).thenReturn(pe);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pointsEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pe))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
    }

    //get all
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
