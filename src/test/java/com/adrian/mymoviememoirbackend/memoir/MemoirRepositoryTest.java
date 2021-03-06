package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.Person.Person;
import com.adrian.mymoviememoirbackend.cinema.Cinema;
import com.adrian.mymoviememoirbackend.statics.EntryPerMonth;
import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import com.adrian.mymoviememoirbackend.statics.MovieAndRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemoirRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private MemoirRepository repository;

    private List<Person> person = new ArrayList<>();
    private List<Cinema> cinema = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Person person = new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166);
        this.person.add(this.manager.persist(person));

        this.cinema.add(this.manager.persist(
                new Cinema("Hoyts", "3140"))
        );
        this.cinema.add(this.manager.persist(
                new Cinema("Hoyts", "3141"))
        );
        this.cinema.add(this.manager.persist(
                new Cinema("Hoyts", "3142"))
        );

        this.manager.persist(
                new Memoir(
                        "Movie 1",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                        "Good",
                        5f,
                        this.cinema.get(0),
                        this.person.get(0)
                )
        );
        this.manager.persist(
                new Memoir(
                        "Movie 1",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 3, 12, 12, 12),
                        "Good",
                        4f,
                        this.cinema.get(0),
                        this.person.get(0)
                )
        );
        this.manager.persist(
                new Memoir(
                        "Movie 1",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 27, 12, 12, 12),
                        "Good",
                        3f,
                        this.cinema.get(0),
                        this.person.get(0)
                )
        );
        this.manager.persist(
                new Memoir(
                        "Movie 1",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear()-1, Month.APRIL, 3, 12, 12, 12),
                        "Good",
                        2f,
                        this.cinema.get(0),
                        this.person.get(0)
                )
        );

        this.manager.persist(
                new Memoir(
                        "Movie 3",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.APRIL, 1, 12, 12, 12),
                        "Good",
                        2.5f,
                        this.cinema.get(1),
                        this.person.get(0)
                )
        );

        this.manager.persist(
                new Memoir(
                        "Movie 4",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.APRIL, 5, 12, 12, 12),
                        "Good",
                        1f,
                        this.cinema.get(2),
                        this.person.get(0)
                )
        );

        this.manager.persist(
                new Memoir(
                        "Movie 5",
                        LocalDate.of(2019, Month.MARCH, 2),
                        LocalDateTime.of(LocalDate.now().getYear(), Month.APRIL, 6, 12, 12, 12),
                        "Good",
                        1.5f,
                        this.cinema.get(2),
                        this.person.get(0)
                )
        );

    }

    @Test
    void findByUID() {
        Optional<List<Memoir>> result = repository.findByUID(this.person.get(0).getUserId());
        assertTrue(result.isPresent());

        List<Memoir> memoirs = result.get();
        assertTrue(memoirs.size() > 0);
        assertEquals(this.person.get(0).getFirstName(), memoirs.get(0).getUser().getFirstName());
    }

    @Test
    void findByMovieNameCinemaName() {
        Optional<List<Memoir>> result = repository.findByMovieNameAndCinema("Movie 1", this.cinema.get(0).getName());
        assertTrue(result.isPresent());

        List<Memoir> memoirs = result.get();
        assertTrue(memoirs.size() == 4);
        assertEquals(this.person.get(0).getFirstName(), memoirs.get(0).getUser().getFirstName());
    }

    @Test
    void findByMovieNameAndCinemaPostcode() {
        Optional<List<Memoir>> result = repository.findByMovieNameAndCinemaPostcode("Movie 1", this.cinema.get(0).getPostcode());
        assertTrue(result.isPresent());

        List<Memoir> memoirs = result.get();
        assertTrue(memoirs.size() == 4);
        assertEquals("Movie 1", memoirs.get(0).getMovieName());
    }

    @Test
    void countByIdDatePerPostcode(){
        Optional<List<MemoirPostcodeCount>> result = repository.countByIdDatePerPostcode(this.person.get(0).getUserId(),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MARCH, 29, 0,0,0),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 1, 0,0,0));
        assertTrue(result.isPresent());

        List<MemoirPostcodeCount> counts = result.get();
        assertTrue(counts.size() == 2);
        assertEquals(cinema.get(1).getPostcode(), counts.get(0).getPostcode());
        assertEquals(counts.get(0).getTotal(), 1);

        assertEquals(cinema.get(2).getPostcode(), counts.get(1).getPostcode());
        assertEquals(counts.get(1).getTotal(), 2);

    }

    @Test
    void findHighestRatingbyUserid(){
        Optional<List<MovieAndRating>> result = repository.findTopRatingbyUserid(this.person.get(0).getUserId(), PageRequest.of(0, 1));
        assertTrue(result.isPresent());

        List<MovieAndRating> resultList = result.get();
        assertTrue(resultList.size() == 1);
        assertEquals(5, resultList.get(0).getRating());
    }

    @Test
    void topFiveMovieOfTheYear(){
        Optional<List<MovieAndRating>> result = repository.topFiveMovieOfTheYear(this.person.get(0).getUserId(), LocalDate.now().getYear(), PageRequest.of(0, 5));
        assertTrue(result.isPresent());

        List<MovieAndRating> resultList = result.get();
        assertTrue(resultList.size() == 5);
        assertEquals(5, resultList.get(0).getRating());
        assertEquals(1.5, resultList.get(4).getRating());
    }

    @Test
    void countMoviePerMonth(){
        Optional<List<EntryPerMonth>> result = repository.countMoviePerMonth(this.person.get(0).getUserId(), LocalDate.now().getYear());
        assertTrue(result.isPresent());

        List<EntryPerMonth> resultList = result.get();
        assertTrue(resultList.size() == 2);
        assertEquals(3, resultList.get(0).getCount());
        assertEquals(3, resultList.get(1).getCount());
    }
}