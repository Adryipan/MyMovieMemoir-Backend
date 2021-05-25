package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoirRepository extends JpaRepository<Memoir, Long> {

    @Query("SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:name)")
    Optional<List<Memoir>> findByMovieName(String name);

    @Query("SELECT m FROM Memoir m WHERE m.releaseDate = :releaseDate")
    Optional<List<Memoir>> findByReleaseDate(LocalDate releaseDate);

    @Query("SELECT m FROM Memoir m WHERE m.watchDateTime = :watchDateTime")
    Optional<List<Memoir>> findByWatchTime(LocalDateTime watchDateTime);

    @Query("SELECT m FROM Memoir m WHERE m.rating = :rating")
    Optional<List<Memoir>> findByRating(float rating);

    @Query("SELECT m FROM Memoir m WHERE m.person.id = :userID")
    Optional<List<Memoir>> findByUID(long userID);

    @Query("SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:movieName) and UPPER(m.cinema.name) = UPPER(:cinemaName)")
    Optional<List<Memoir>> findByMovieNameAndCinema(String movieName, String cinemaName);

    @Query("SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:movieName) and m.cinema.postcode = :postcode")
    Optional<List<Memoir>> findByMovieNameAndCinemaPostcode(String movieName, String postcode);

    @Query("SELECT new com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount(m.cinema.postcode, COUNT(m.cinema.postcode)) " +
            "FROM Memoir m " +
            "WHERE m.person.id = :userId " +
            "AND m.watchDateTime BETWEEN :startDateTime AND :endDateTime " +
            "GROUP BY m.cinema.postcode")
    Optional<List<MemoirPostcodeCount>> countByIdDatePerPostcode(long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
