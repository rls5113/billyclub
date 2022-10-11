package com.billyclub.points.controller;

import com.billyclub.points.model.Player;
import com.billyclub.points.model.assembler.PlayerModelAssembler;
import com.billyclub.points.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PlayerController.class)
@AutoConfigureMockMvc
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService service;
    @MockBean
    private PlayerModelAssembler assembler;
    @Autowired ObjectMapper objectMapper;
    //get by id
    @Test
    public void getById_shouldReturnOneEvent() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Player p = new Player(1000L,"Herman Munster",25,27, now);
        EntityModel<Player> model = EntityModel.of(p);
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();


        when(service.findById(any())).thenReturn(p);
        when(assembler.toModel(any())).thenReturn(model);
        System.out.println(objectMapper.writeValueAsString(p));
        ResultActions result =
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/players/{id}",1000L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(model))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000L))
                .andExpect(jsonPath("$.name").value(p.getName()))
                .andExpect(jsonPath("$.pointsThisEvent").value(p.getPointsThisEvent()))
                .andExpect(jsonPath("$.pointsToPull").value(p.getPointsToPull()))
                .andExpect(jsonPath("$.timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
        ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //update
    @Test
    public void updatePointsEvent_shouldChangeEventValues() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Player p = new Player(1000L,"Herman Munster",25,27, now);
        Player p1 = new Player(1000L,"Hermie Munster",21,23, now);

        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<Player> model = EntityModel.of(p1);

        when(service.findById(any())).thenReturn(p);
        when(service.save(any(Player.class))).thenReturn(p1);
        when(assembler.toModel(any())).thenReturn(model);

        ResultActions result =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/players/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000L))
                .andExpect(jsonPath("$.name").value(p.getName()))
                .andExpect(jsonPath("$.pointsThisEvent").value(p.getPointsThisEvent()))
                .andExpect(jsonPath("$.pointsToPull").value(p.getPointsToPull()))
                .andExpect(jsonPath("$.timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
        ;
        result.andDo(MockMvcResultHandlers.print());

    }
    //delete
    @Test
    public void deletePointsEvent_shouldRemoveEventRecord() throws Exception {
        Player p = new Player(1000L,"Herman Munster",25,27, LocalDateTime.now());
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<Player> model = EntityModel.of(p);

        when(service.findById(any())).thenReturn(p);
        when(service.save(any(Player.class))).thenReturn(p);
        when(assembler.toModel(any())).thenReturn(model);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/players/1000")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1000L))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //post
    @Test
    public void addNewPointsEvent_shouldCreateAndReturnEvent() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Player p = new Player(1000L,"Herman Munster",25,27, LocalDateTime.now());
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        EntityModel<Player> model = EntityModel.of(p);

        when(service.findById(any())).thenReturn(p);
        when(service.save(any(Player.class))).thenReturn(p);
        when(assembler.toModel(any())).thenReturn(model);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/players")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1000L))
                        .andExpect(jsonPath("$.name").value(p.getName()))
                        .andExpect(jsonPath("$.pointsThisEvent").value(p.getPointsThisEvent()))
                        .andExpect(jsonPath("$.pointsToPull").value(p.getPointsToPull()))
                        .andExpect(jsonPath("$.timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }

    //get all
    @Test
    public void getAllEvents_shouldReturnAllEvent() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        List<Player> players = new ArrayList<>();
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        String[] names = {"Herman Munster","Lilly Munster","Eddie Munster"};
        Integer[] points = {25,20,15};
        Integer[] actual = {27,18,18};
        for (int i = 0; i < 3; i++) {
            long id = i + 1000;
            Player p = new Player(id,names[i], points[i],actual[i],now);
            players.add(p);
        }
        for (Player player : players) {
            when(assembler.toModel(player)).thenReturn(EntityModel.of(player));
        }

        System.out.println(objectMapper.writeValueAsString(players));

        when(service.getAll()).thenReturn(players);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/players")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(players))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$._embedded.players.size()").value(3))
                        .andExpect(jsonPath("$._embedded.players[0].id").value(1000L))
                        .andExpect(jsonPath("$._embedded.players[0].name").value("Herman Munster"))
                        .andExpect(jsonPath("$._embedded.players[0].pointsThisEvent").value(27))
                        .andExpect(jsonPath("$._embedded.players[0].pointsToPull").value(25))
                        .andExpect(jsonPath("$._embedded.players[0].timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                        .andExpect(jsonPath("$._embedded.players[1].id").value(1001L))
                        .andExpect(jsonPath("$._embedded.players[1].name").value("Lilly Munster"))
                        .andExpect(jsonPath("$._embedded.players[1].pointsThisEvent").value(18))
                        .andExpect(jsonPath("$._embedded.players[1].pointsToPull").value(20))
                        .andExpect(jsonPath("$._embedded.players[1].timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                        .andExpect(jsonPath("$._embedded.players[2].id").value(1002L))
                        .andExpect(jsonPath("$._embedded.players[2].name").value("Eddie Munster"))
                        .andExpect(jsonPath("$._embedded.players[2].pointsThisEvent").value(18))
                        .andExpect(jsonPath("$._embedded.players[2].pointsToPull").value(15))
                        .andExpect(jsonPath("$._embedded.players[2].timeEntered").value(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                ;
        result.andDo(MockMvcResultHandlers.print());
    }
}
