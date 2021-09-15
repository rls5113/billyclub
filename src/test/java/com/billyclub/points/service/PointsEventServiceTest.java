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

import java.time.LocalDateTime;
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
        PointsEvent pe = new PointsEvent(1, LocalDateTime.of(1962,3,2,0,0),3);
        List<PointsEvent> list = new ArrayList<>();
        list.add(pe);
        given(pointsEventRepository.findAll()).willReturn(list);
        List<PointsEvent> result = pointsEventService.getAllPointsEvents();

        assertThat(result.size()).isEqualTo(Integer.valueOf("1"));

    }

    @Test  //findbyid
    public void getEventById_returnsCorrectEvent() throws Exception {
        PointsEvent pe = new PointsEvent(1, LocalDateTime.of(1962,3,2,0,0),3);
        given(pointsEventRepository.findById(1l)).willReturn(java.util.Optional.of(pe));
        PointsEvent result = pointsEventService.getPointsEventById(1l);

        assertThat(result.getNumOfTimes()).isEqualTo(Integer.valueOf("3"));
    }

    @Test  //put
    public void putSaves_returnsEventChanges() throws Exception {
        PointsEvent pe = new PointsEvent(1, LocalDateTime.of(1962,3,2,0,0),3);
        given(pointsEventRepository.findById(1l)).willReturn(java.util.Optional.of(pe));
        PointsEvent result = pointsEventService.getPointsEventById(1l);
        result.setNumOfTimes(6);
        given(pointsEventRepository.save(any(PointsEvent.class))).willReturn(result);
        PointsEvent changed = pointsEventService.savePointsEvent(result);

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
