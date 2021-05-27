package com.adrian.mymoviememoirbackend.cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CinemaServiceTest {

    @Mock
    private CinemaRepository repository;

    @InjectMocks
    private CinemaService service;

    @Test
    void findById() {
        final Long id = 1L;
        Cinema cinema = new Cinema("Hoyts Chadstone", "3148");

        given(repository.findById(1L)).willReturn(Optional.of(cinema));

        final Cinema expected = service.findById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByCinemaName() {
        final String cinemaName = "Hoyts Chadstone";
        List<Cinema> data = new ArrayList<>();
        data.add(
                new Cinema("Hoyts Chadstone", "3148")
        );

        given(repository.findByCinemaName(cinemaName)).willReturn(Optional.of(data));

        final List<Cinema> expected = service.findByCinemaName(cinemaName);
        assertThat(expected).isNotNull();
        for(Cinema c : expected){
            assertEquals(cinemaName, c.getName());
        }
    }

    @Test
    void findBySuburb() {
        final String postcode = "3148";
        List<Cinema> data = new ArrayList<>();
        data.add(
                new Cinema("Hoyts Chadstone", "3148")
        );

        given(repository.findByPostcode(postcode)).willReturn(Optional.of(data));

        final List<Cinema> expected = service.findByPostcode(postcode);
        assertThat(expected).isNotNull();
        for(Cinema c : expected){
            assertEquals(postcode, c.getPostcode());
        }
    }

    @Test
    void shouldThrowError(){
        final Long id = 4L;
        Exception exception = assertThrows(IllegalStateException.class, () ->{
            service.findById(id);
        });

        String expectedMessage = "Cinema with id " + id + " does not exist.";
        assertTrue(exception.getMessage().contains(expectedMessage));

        final String name = "chadstone";
        exception = assertThrows(IllegalStateException.class, () ->{
            service.findByCinemaName(name);
        });

        expectedMessage = "Cinema with name " + name + " does not exist.";
        assertTrue(exception.getMessage().contains(expectedMessage));

    }
}