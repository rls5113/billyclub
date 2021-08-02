package com.billyclub.points.repo;

import com.billyclub.points.model.PointsEvent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PointsEventRepositoryTest {

    @Autowired
    PointsEventRepository pointsEventRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByDate_returnsOne() throws Exception {
        System.out.println("braekpoint....");
        LocalDateTime ldt = LocalDateTime.of(1962,3,2,0,0);
        PointsEvent pe = new PointsEvent(1, ldt ,3);
        testEntityManager.persistAndFlush(pe);
        PointsEvent result = pointsEventRepository.findByEventDate(ldt);

        assertThat(result.getEventDate()).isEqualTo(pe.getEventDate());
    }

}
