package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.statics.EntryPerMonth;
import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import com.adrian.mymoviememoirbackend.statics.MovieAndRating;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/api/v1/memoir")
public class MemoirController {
    
    private final MemoirService service;

    @Autowired
    public MemoirController(MemoirService service) {
        this.service = service;
    }

    @GetMapping
    public List<Memoir> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "{id}")
    public Memoir findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    //Get by name
    @GetMapping(path = "findByMovieName/{movieName}")
    public List<Memoir> findByMovieName(@PathVariable("movieName") String movieName){
        return service.findByMovieName(movieName.replace("-", " "));
    }

    //Get by release date
    @GetMapping(path = "findByReleaseDate/{releaseDate}")
    public List<Memoir> findByReleaseDate(@PathVariable("releaseDate") String releaseDate){
        return service.findByReleaseDate(releaseDate);
    }

    //Get by Watch time
    @GetMapping(path = "findByWatchDate/{watchDate}")
    public List<Memoir> findByWatchDate(@PathVariable("watchTime") String watchDate){
        return service.findByWatchDateTime(watchDate);
    }

    //Get by rating
    @GetMapping(path = "findByRating/{rating}")
    public List<Memoir> findByRating(@PathVariable("rating") float rating){
        return service.findByRating(rating);
    }

    //Get by userID
    @GetMapping(path = "findByUID/{userID}")
    public List<Memoir> findByUID(@PathVariable("userID") long userID){
        return service.findByUID(userID);
    }

    //Get by movie name and cinema name
    @GetMapping(path = "findByMovieNameAndCinema/{movieName}/{cinemaName}")
    public List<Memoir> findByMovieNameAndCinema(@PathVariable("movieName") String movieName,
                                                 @PathVariable("cinemaName") String cinemaName){
        return service.findByMovieNameAndCinema(movieName.replace("-", " "), cinemaName.replace("-", " "));
    }

    //Get by movie name and cinema postcode
    @GetMapping(path = "findByMovieNameAndCinemaPostcode/{movieName}/{postcode}")
    public ResponseEntity<String> findByMovieNameAndCinemaPostcode(@PathVariable("movieName") String movieName,
                                                       @PathVariable("postcode") String postcode){
        String regex = "^\\d{4}";
        Pattern postCodePattern = Pattern.compile(regex);
        String response = "";
        if(postCodePattern.matcher(postcode).matches()){
            List<Memoir> result = service.findByMovieNameAndCinemaPostcode(movieName.replace("-", " "), postcode);
            Gson gson = new Gson();
            response = gson.toJson(result);
            return new ResponseEntity(response, HttpStatus.OK);
        }else{
            response = "{\"Message\": \"Invalid Postcode\"}";
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }

    //id+start+enddate to find number of entry per postcode
    @GetMapping(path = "countByIdDatePerPostcode/{userId}/{startDate}/{endDate}")
    public ResponseEntity<String> countByIdDatePerPostcode(@PathVariable("userId") long userId,
                                       @PathVariable("startDate") String startDate,
                                       @PathVariable("endDate") String endDate){
        // Check date format
        String regex = "\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*";
        Pattern datePattern = Pattern.compile(regex);
        String response = "";
        boolean correctStartDate = datePattern.matcher(startDate).matches();
        boolean correctEndDate = datePattern.matcher(endDate).matches();
        if( correctStartDate && correctEndDate) {
            List<MemoirPostcodeCount> result = service.countByIdDatePerPostcode(userId, startDate, endDate);
            Gson gson = new Gson();
            response = gson.toJson(result);
            return new ResponseEntity(response, HttpStatus.OK);
        }else{
            String message = "";
            if(!correctStartDate){
                message += "start date format; ";
            }
            if(!correctEndDate){
                message += "end date format; ";
            }
            response = "{\"Message\": \"Incorrect " + message + "Please follow format yyyy-mm-dd in numbers.\"}";
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //id+year to find number of entry per month
    @GetMapping(path = "countMoviePerMonth/{userId}/{year}")
    public ResponseEntity<String> countMoviePerMonth(@PathVariable("userId") long userId, @PathVariable("year") int year){
        List<EntryPerMonth> result = service.countMoviePerMonth(userId, year);


        Gson gson = new Gson();
        String response = gson.toJson(result);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    //id to find highest rating
    @GetMapping(path = "findHighestRatingbyUserid/{userId}")
    public ResponseEntity<String> findHighestRatingbyUserid(@PathVariable("userId") long userId){
        List<MovieAndRating> result = service.findTopRatingbyUserid(userId, 1);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    //id to find top five of movies released in the current year
    @GetMapping(path = "topFiveMovieOfTheYear/{userId}")
    public ResponseEntity<String> topFiveMovieOfTheYear(@PathVariable("userId") long userId){
        List<MovieAndRating> result = service.topFiveMovieOfTheYear(userId);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
