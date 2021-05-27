package com.adrian.mymoviememoirbackend.cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class CinemaRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private CinemaRepository repository;

    @BeforeEach
    void setUp() {
        this.manager.persist(
                new Cinema("Hoyts Chadstone", "3148")
        );

        this.manager.persist(
                new Cinema("Waverley village", "3149")
        );
    }

    @Test
    void findByCinemaName() {
        Optional<List<Cinema>> result = this.repository.findByCinemaName("Hoyts Chadstone");
        assertTrue(result.isPresent());

        result = this.repository.findByCinemaName("hoyts chadstone");
        assertTrue(result.isPresent());
    }

    @Test
    void findBySuburb() {
        Optional<List<Cinema>> result = this.repository.findByPostcode("3148");
        assertTrue(result.isPresent());

        for(Cinema thisCinema : result.get()){
            assertEquals("Hoyts Chadstone", thisCinema.getName());
            assertEquals("3148", thisCinema.getPostcode());
        }
    }

    @Test
    void shouldReturnEmpty(){
        Optional<Cinema> result = this.repository.findById(3L);
        assertTrue(result.isEmpty());

        Optional<List<Cinema>> resultList = this.repository.findByCinemaName("Chadstone");
        assertTrue(resultList.get().isEmpty());

        resultList = this.repository.findByPostcode("3000");
        assertTrue(resultList.get().isEmpty());
    }
}