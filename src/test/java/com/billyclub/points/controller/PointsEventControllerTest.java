package com.billyclub.points.controller;

import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.model.assembler.PointsEventModelAssembler;
import com.billyclub.points.service.PointsEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@AutoConfigureJsonTesters
@WebMvcTest(PointsEventController.class)
@AutoConfigureMockMvc
public class PointsEventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PointsEventService pointsEventService;
    @MockBean
    private PointsEventModelAssembler assembler;
    @Autowired ObjectMapper objectMapper;
    //get by id
    @Test
    public void getById_shouldReturnOneEvent() throws Exception {
        LocalDate ld = LocalDate.now().plusDays(8);
        LocalTime lt = LocalTime.parse("06:30:00",DateTimeFormatter.ofPattern("HH:mm:ss"));       //of(6,30,00);
        PointsEvent pe = new PointsEvent(1003L, ld, lt, 6);
        EntityModel<PointsEvent> model = EntityModel.of(pe);
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        when(pointsEventService.findById(any())).thenReturn(pe);
        when(assembler.toModel(any())).thenReturn(model);
        System.out.println(objectMapper.writeValueAsString(pe));
        ResultActions result =
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvents/{id}",1003L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(model))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1003L))
                .andExpect(jsonPath("$.eventDate").value(ld.toString()))
                .andExpect(jsonPath("$.startTime").value("06:30:00"))
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //update
    @Test
    public void updatePointsEvent_shouldChangeEventValues() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
//        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000L,ld,lt,2);

        LocalDate ld1 = LocalDate.of(2021, Month.APRIL,8);
        LocalTime lt1 = LocalTime.of(6, 30,0);
        PointsEvent pe1 = new PointsEvent(1000L,ld1,lt1,6);
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<PointsEvent> model = EntityModel.of(pe1);

        when(pointsEventService.findById(any())).thenReturn(pe);
        when(pointsEventService.save(any(PointsEvent.class))).thenReturn(pe1);
        when(assembler.toModel(any())).thenReturn(model);

        ResultActions result =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/pointsEvents/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pe1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000L))
                .andExpect(jsonPath("$.eventDate").value(ld1.toString()))
                .andExpect(jsonPath("$.startTime").value("06:30:00"))
                .andExpect(jsonPath("$.numOfTimes").value("6"))
        ;
        result.andDo(MockMvcResultHandlers.print());

    }
    //delete
    @Test
    public void deletePointsEvent_shouldRemoveEventRecord() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
//        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000L,ld,lt,2);
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<PointsEvent> model = EntityModel.of(pe);

        when(pointsEventService.findById(any())).thenReturn(pe);
        when(pointsEventService.save(any(PointsEvent.class))).thenReturn(pe);
        when(assembler.toModel(any())).thenReturn(model);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pointsEvents/1000")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(pe))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1000L))
                        .andExpect(jsonPath("$.eventDate").value(ld.toString()))
                        .andExpect(jsonPath("$.startTime").value("06:30:00"))
                        .andExpect(jsonPath("$.numOfTimes").value(2))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //post
    @Test
    public void addNewPointsEvent_shouldCreateAndReturnEvent() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
//        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL,2,0,0);
        PointsEvent pe = new PointsEvent(1000L,ld,lt,2);
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<PointsEvent> model = EntityModel.of(pe);

        when(pointsEventService.findById(any())).thenReturn(pe);
        when(pointsEventService.save(any(PointsEvent.class))).thenReturn(pe);
        when(assembler.toModel(any())).thenReturn(model);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pointsEvents")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(pe))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1000L))
                        .andExpect(jsonPath("$.eventDate").value(ld.toString()))
                        .andExpect(jsonPath("$.startTime").value("06:30:00"))
                        .andExpect(jsonPath("$.numOfTimes").value(2))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //get all
    @Test
    public void getAllEvents_shouldReturnAllEvent() throws Exception {
        List<PointsEvent> events = new ArrayList<>();
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        for (int i = 0; i < 3; i++) {
//            long id = i;
            LocalDate ld = LocalDate.of(2022, Month.APRIL, 2+i);
            LocalTime lt = LocalTime.of(6, 30, 0);
            PointsEvent pe = new PointsEvent(Long.valueOf(i), ld, lt, i + 1);
            events.add(pe);
        }
        for (PointsEvent event : events) {
            when(assembler.toModel(event)).thenReturn(EntityModel.of(event));
        }

        System.out.println(objectMapper.writeValueAsString(events));

        when(pointsEventService.getAll()).thenReturn(events);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pointsEvents"))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(events))
//                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$._embedded.pointsEvents.size()").value(3))
                        .andExpect(jsonPath("$._embedded.pointsEvents[0].eventDate").value("2022-04-02"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[0].startTime").value("06:30:00"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[0].numOfTimes").value("1"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[1].eventDate").value("2022-04-03"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[1].startTime").value("06:30:00"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[1].numOfTimes").value("2"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[2].eventDate").value("2022-04-04"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[2].startTime").value("06:30:00"))
                        .andExpect(jsonPath("$._embedded.pointsEvents[2].numOfTimes").value("3"))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }
}
