package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.Person.Person;
import com.adrian.mymoviememoirbackend.Person.PersonRepository;
import com.adrian.mymoviememoirbackend.cinema.Cinema;
import com.adrian.mymoviememoirbackend.cinema.CinemaRepository;
import com.adrian.mymoviememoirbackend.cinema.CinemaService;
import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemoirServiceTest {

    @Mock
    private MemoirRepository memoirRepository;

    @InjectMocks
    private MemoirService service;


    @Test
    void countByIdDatePerPostcode() {
        List<Memoir> memoirList = new ArrayList<>();
        memoirList.add(new Memoir(
                "Movie 1",
                LocalDate.of(2019, Month.MARCH, 2),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                "Good",
                5f,
                new Cinema(1, "Hoyts", "3140"),
                new Person(1, "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166)
        ));

        memoirList.add(new Memoir(
                "Movie 1",
                LocalDate.of(2019, Month.MARCH, 2),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                "Good",
                5f,
                new Cinema(1, "Hoyts", "3140"),
                new Person(1, "John",
                        "Smith",
                        "M",
                        LocalDate.of(1995, Month.MARCH, 29),
                        "16 Coane Street",
                        "VIC",
                        3166)
        ));

        memoirList.add(new Memoir(
                "Movie 1",
                LocalDate.of(2019, Month.MARCH, 2),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                "Good",
                5f,
                new Cinema(1,"Hoyts", "3141"),
                new Person(1,
                        "John",
                        "Smith",
                        "M",
                        LocalDate.of(1995, Month.MARCH, 29),
                        "16 Coane Street",
                        "VIC",
                        3166)
        ));

        List<MemoirPostcodeCount> expected = new ArrayList<>();
        expected.add(new MemoirPostcodeCount("3140", 2));
        expected.add(new MemoirPostcodeCount("3141", 1));


        final String startDateString = "2019-5-1";
        final String endDateString = "2022-5-1";

        final LocalDateTime startDate= LocalDateTime.of(2019, 5, 1, 0,0,0);
        final LocalDateTime endDate = LocalDateTime.of(2022, 5, 1, 23,59,59);

        //Postcode: 3140:2 3141:1
        given(memoirRepository.countByIdDatePerPostcode(1, startDate, endDate)).willReturn(Optional.of(expected));

        final List<MemoirPostcodeCount> actual = service.countByIdDatePerPostcode(1, startDateString, endDateString);
        assertThat(actual).isNotNull();

        assertEquals("3140", actual.get(0).getPostcode());
        assertEquals(2, actual.get(0).getTotal());

        assertEquals("3141", actual.get(1).getPostcode());
        assertEquals(1, actual.get(1).getTotal());

    }

}