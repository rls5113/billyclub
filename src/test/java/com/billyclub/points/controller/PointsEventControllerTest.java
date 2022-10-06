package com.billyclub.points.controller;

import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.service.PointsEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @MockBean
    PointsEventModelAssembler assembler;
    //get by id
    @Test
    public void getById_shouldReturnOneEvent() throws Exception {
        LocalDate ld = LocalDate.now().plusDays(8);
        LocalTime lt = LocalTime.of(6,30,0);
        PointsEvent pe = new PointsEvent(1001l, ld, lt, 6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        System.out.println(mapper.writeValueAsString(pe));
        when(pointsEventService.findById(any())).thenReturn(pe);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvents/1003")
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
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
//        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000l,ld,lt,2);

        LocalDate ld1 = LocalDate.of(2021, Month.APRIL,8);
        LocalTime lt1 = LocalTime.of(6, 30,0);
        PointsEvent pe1 = new PointsEvent(1000l,ld1,lt1,6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        when(pointsEventService.findById(any())).thenReturn(pe);
        when(pointsEventService.save(any(PointsEvent.class))).thenReturn(pe1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/pointsEvents/1000")
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

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pointsEvents/1000"))
                .andExpect(status().isOk())
        ;
    }

    //post
    @Test
    public void addNewPointsEvent_shouldCreateAndReturnEvent() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
//        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent();
        pe.setEventDate(ld);
        pe.setStartTime(lt);
        pe.setNumOfTimes(6);
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

//        System.out.println(mapper.writeValueAsString(pe));
        when(pointsEventService.save(any(PointsEvent.class))).thenReturn(pe);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pointsEvents")
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
            long id = i;
            LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
            LocalTime lt = LocalTime.of(6,30,0);
            PointsEvent pe = new PointsEvent(id, ld, lt ,i+1);
            events.add(pe);
        }
        given(pointsEventService.getAll()).willReturn(events);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numOfTimes").value("1"))
                .andExpect(jsonPath("$[1].numOfTimes").value("2"))
                .andExpect(jsonPath("$[2].numOfTimes").value("3"))
        ;
    }

}
