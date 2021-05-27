package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.statics.EntryPerMonth;
import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import com.adrian.mymoviememoirbackend.statics.MovieAndRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemoirService {

    private final MemoirRepository repository;

    @Autowired
    public MemoirService(MemoirRepository repository) {
        this.repository = repository;
    }

    public List<Memoir> findAll() {
        return repository.findAll();
    }


    public Memoir findById(long id) {
        Optional<Memoir> result = repository.findById(id);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir with id " + id + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByMovieName(String name) {
        Optional<List<Memoir>> result = repository.findByMovieName(name);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir with movie name " + name + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByReleaseDate(String releaseDate) {
        String[] releaseDateString = releaseDate.split("-");
        Optional<List<Memoir>> result = repository.findByReleaseDate(LocalDate.of(Integer.parseInt(releaseDateString[0]), Integer.parseInt(releaseDateString[1]), Integer.parseInt(releaseDateString[2])));
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir with movie released on " + releaseDate + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByWatchTime(String watchTime) {
        String[] watchTimeString = watchTime.split("-");
        Optional<List<Memoir>> result = repository.findByWatchTime(LocalDateTime.of(Integer.parseInt(watchTimeString[0]), Integer.parseInt(watchTimeString[1]), Integer.parseInt(watchTimeString[2]), Integer.parseInt(watchTimeString[3]), Integer.parseInt(watchTimeString[4])));
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir with movie watched at " + watchTime + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByRating(float rating) {
        Optional<List<Memoir>> result = repository.findByRating(rating);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir with movie name rated " + rating + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByUID(long userID) {
        Optional<List<Memoir>> result = repository.findByUID(userID);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of user id " + userID + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByMovieNameAndCinema(String movieName, String cinemaName) {
        Optional<List<Memoir>> result = repository.findByMovieNameAndCinema(movieName, cinemaName);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of movie " + movieName + " watched in cinema " + cinemaName + " does not exist.");
        }
        return result.get();
    }

    public List<Memoir> findByMovieNameAndCinemaPostcode(String movieName, String postcode) {
        Optional<List<Memoir>> result = repository.findByMovieNameAndCinemaPostcode(movieName, postcode);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of movie " + movieName + " watched in postcode " + postcode + " does not exist.");
        }
        return result.get();
    }

    public List<MemoirPostcodeCount> countByIdDatePerPostcode(long userId, String startDate, String endDate) {
        // Process the date
        String[] startDateString = startDate.split("-");
        String[] endDateString = endDate.split("-");
        LocalDateTime startDateTime = LocalDateTime.of(Integer.parseInt(startDateString[0]), Integer.parseInt(startDateString[1]), Integer.parseInt(startDateString[2]),0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(Integer.parseInt(endDateString[0]), Integer.parseInt(endDateString[1]), Integer.parseInt(endDateString[2]),23, 59, 59);

        Optional<List<MemoirPostcodeCount>> result = repository.countByIdDatePerPostcode(userId, startDateTime, endDateTime);

        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of user " + userId + " watched between " + startDate + " and " + endDate + " does not exist.");
        }

        return result.get();

    }

    public List<MovieAndRating> findTopRatingbyUserid(long userId, int limit) {
        Optional<List<MovieAndRating>> result = repository.findTopRatingbyUserid(userId, PageRequest.of(0, limit));
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of user " + userId + " does not exist.");
        }

        return result.get();
    }

    public List<MovieAndRating> topFiveMovieOfTheYear(long userId) {
        int currentYear = LocalDate.now().getYear();
        Optional<List<MovieAndRating>> result = repository.topFiveMovieOfTheYear(userId, currentYear, PageRequest.of(0, 5));
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of user " + userId + " does not exist.");
        }

        return result.get();
    }

    public List<EntryPerMonth> countMoviePerMonth(long userId, int year) {
        Optional<List<EntryPerMonth>> result = repository.countMoviePerMonth(userId, year);
        if(result.isEmpty()){
            throw new IllegalStateException("Memoir of user " + userId + " in year " + year + " does not exist.");
        }

        return result.get();
    }
}
