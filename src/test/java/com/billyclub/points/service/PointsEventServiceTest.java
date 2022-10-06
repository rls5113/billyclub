package com.billyclub.points.service;

import com.billyclub.points.model.PointsEvent;
import com.billyclub.points.repo.PointsEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PointsEventServiceTest {

    @Mock
    private PointsEventRepository pointsEventRepository;
    @InjectMocks
    private PointsEventService pointsEventService;

//    @Before
//    public void setUp() throws Exception {
//        pointsEventService = new PointsEventService(pointsEventRepository);
//    }


    @Test   //findall
    public void getEventDetails_returnsEventInfo() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);

        PointsEvent pe = new PointsEvent(ld,lt,3);
        List<PointsEvent> list = new ArrayList<>();
        list.add(pe);
        given(pointsEventRepository.findAll()).willReturn(list);
        List<PointsEvent> result = pointsEventService.getAll();

        assertThat(result.size()).isEqualTo(Integer.valueOf("1"));

    }

    @Test  //findbyid
    public void getEventById_returnsCorrectEvent() throws Exception {
        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
        PointsEvent pe = new PointsEvent(ld,lt,3);
        given(pointsEventRepository.findById(1000l)).willReturn(java.util.Optional.of(pe));
        PointsEvent result = pointsEventService.findById(1000l);

        assertThat(result.getEventDate()).isEqualTo(ld);
        assertThat(result.getStartTime()).isEqualTo(lt);
        assertThat(result.getNumOfTimes()).isEqualTo(Integer.valueOf("3"));
    }

    @Test  //put
    public void putSaves_returnsEventChanges() throws Exception {

        LocalDate ld = LocalDate.of(2022, Month.APRIL,2);
        LocalTime lt = LocalTime.of(6,30,0);
        PointsEvent pe = new PointsEvent(1000l,ld,lt,3);

        given(pointsEventRepository.findById(1000l)).willReturn(java.util.Optional.of(pe));
        PointsEvent result = pointsEventService.findById(1000l);
        result.setNumOfTimes(6);
        given(pointsEventRepository.save(any(PointsEvent.class))).willReturn(result);
        PointsEvent changed = pointsEventService.save(result);

        assertThat(changed.getNumOfTimes()).isEqualTo(Integer.valueOf("6"));
    }

//    @Test  //delete
//    public void delete_removesRecord() throws Exception {
//        PointsEvent pe = new PointsEvent(1, LocalDateTime.of(1962,3,2,0,0),3);
//        given(pointsEventRepository.deleteById(1l)).willReturn();
//        PointsEvent result = pointsEventService.getPointsEventById(1l);
//        result.setNumOfTimes(6);
//        given(pointsEventRepository.save(any(PointsEvent.class))).willReturn(result);
//        PointsEvent changed = pointsEventService.savePointsEvent(result);
//
//        assertThat(changed.getNumOfTimes()).isEqualTo(Integer.valueOf("6"));
//    }

}
