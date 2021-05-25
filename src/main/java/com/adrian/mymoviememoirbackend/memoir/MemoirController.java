package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    @GetMapping(path = "findByWatchTime/{watchTime}")
    public List<Memoir> findByWatchTime(@PathVariable("watchTime") String watchTime){
        return service.findByWatchTime(watchTime);
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
        return service.findByMovieNameAndCinema(movieName, cinemaName);
    }

    //Get by movie name and cinema postcode
    @GetMapping(path = "findByMovieNameAndCinemaPostcode/{movieName}/{postcode}")
    public List<Memoir> findByMovieNameAndCinemaPostcode(@PathVariable("movieName") String movieName,
                                                       @PathVariable("postcode") String postcode){
        String regex = "^\\d{4}";
        Pattern postCodePattern = Pattern.compile(regex);
        if(postCodePattern.matcher(postcode).matches()){
            return service.findByMovieNameAndCinemaPostcode(movieName, postcode);
        }else{
           throw new IllegalArgumentException("Invalid postcode");
        }

    }

    //id+start+enddate to find number of entry per postcode
    @GetMapping(path = "findByIdDate/{userId}/{startDate}/{endDate}")
    public ResponseEntity<String> countByIdDatePerPostcode(@PathVariable("userId") Integer userId,
                                       @PathVariable("startDate") String startDate,
                                       @PathVariable("endDate") String endDate){

        List<MemoirPostcodeCount> result = service.countByIdDatePerPostcode(userId, startDate, endDate);
        Gson gson = new Gson();
        String response = gson.toJson(result);

        return new ResponseEntity(response, HttpStatus.OK);
    }
//
//    //id+year to find number of entery per month
//    @GetMapping(path = "countMoviePerMonth/{userId}/{year}")
//    @Produces({"application/json"})
//    public Object countMoviePerMonth(@PathVariable("userId") Integer userId, @PathVariable("year") String year){
//        TypedQuery service = em.createQuery("SELECT m.watchDate FROM Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.watchDate) = FUNC('YEAR', :year) ", Object[].class);
//        service.setParameter("userId", userId);
//        try {
//            Date convertedYear = new SimpleDateFormat("yyyy").parse(year);
//            service.setParameter("year", convertedYear);
//        } catch (ParseException ex) {
//            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //Array of month name
//        String[] monthName = new String[13];
//        monthName[1] = "Jan";
//        monthName[2] = "Feb";
//        monthName[3] = "Mar";
//        monthName[4] = "Apr";
//        monthName[5] = "May";
//        monthName[6] = "Jun";
//        monthName[7] = "Jul";
//        monthName[8] = "Aug";
//        monthName[9] = "Sep";
//        monthName[10] = "Oct";
//        monthName[11] = "Nov";
//        monthName[12] = "Dec";
//
//        //Array for counting entries
//        int[] counter = new int[13];
//        Arrays.fill(counter, 0);
//
//        List<Object[]> resultList= service.getResultList();
//        for(int i = 0; i < resultList.size(); i++){
//            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            String thisDate = dateFormat.format(resultList.get(i));
//            int thisMonth = Integer.parseInt(thisDate.split("-")[1]);
//            counter[thisMonth] += 1;
//        }
//
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for (int i = 1; i < monthName.length; i++){
//            JsonObject resultObject = Json.createObjectBuilder().add("Month", monthName[i]).add("Count", counter[i]).build();
//            arrayBuilder.add(resultObject);
//        }
//        JsonArray resultArray = arrayBuilder.build();
//        return resultArray;
//    }
//
//    //id to find highest rating
//    @GetMapping(path = "findHighestRatingbyUserid/{userId}")
//    @Produces({"application/json"})
//    public Object findHighestRatingbyUserid(@PathVariable("userId") Integer userId){
//        TypedQuery service = em.createQuery("SELECT m.movieName, m.rating, m.releaseDate FROM Memoir m WHERE m.userId.userId = :userId", Object[].class);
//        service.setParameter("userId", userId);
//
//        List<Object[]> resultList = service.getResultList();
//
//        short highest = 0;
//        for (Object[] row: resultList){
//            if((short)row[1] > highest){
//                highest = (short)row[1];
//            }
//            if(highest == 5){
//                break;
//            }
//        }
//
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for(Object[] row: resultList){
//            if((short)row[1] == highest){
//                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                JsonObject resultObject = Json.createObjectBuilder().
//                        add("Movie", (String)row[0]).
//                        add("rating", (String)row[1]).
//                        add("Release Date", dateFormat.format(row[2])).build();
//                arrayBuilder.add(resultObject);
//            }
//        }
//        JsonArray resultArray = arrayBuilder.build();
//        return resultArray;
//    }
//
//    //id to find movies watched in the same year as the release year
//    @GetMapping(path =
//    @Path("findWatchedSameAsReleaseYear/{userId}")
//    @Produces({"application/json"})
//    public Object findWatchedSameAsReleaseYear(@PathVariable("userId") Integer userId){
//        TypedQuery service = em.createQuery("SELECT m.movieName, m.releaseDate from Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.releaseDate) = FUNC('YEAR', m.watchDate)", Object[].class);
//        service.setParameter("userId", userId);
//        List<Object[]> resultList = service.getResultList();
//        JsonArrayBuilder arrayBuilder =  Json.createArrayBuilder();
//        for(Object[] row : resultList){
//            DateFormat dateFormat = new SimpleDateFormat("yyyy");
//            JsonObject resultObject = Json.createObjectBuilder().
//                    add("Movie name", (String)row[0]).
//                    add("Release Date", dateFormat.format(row[1])).build();
//            arrayBuilder.add(resultObject);
//        }
//        JsonArray resultArray = arrayBuilder.build();
//        return resultArray;
//
//    }
//
//    //id to find remake movie
//    @GetMapping(path = "findRemakebyUserID/{userId}")
//    @Produces({"application/json"})
//    public Object findRemakebyUserID(@PathVariable("userId") Integer userId){
//        //first filter out the repeated name with different release date
//        TypedQuery filter = em.createQuery("SELECT m.movieName, COUNT(DISTINCT m.releaseDate) FROM Memoir m WHERE m.userId.userId = :userId GROUP BY m.movieName HAVING COUNT(DISTINCT m.releaseDate) > 1", Object[].class);
//        filter.setParameter("userId", userId);
//        List<Object[]> filteredList = filter.getResultList();
//
//        //Select movie name and release date from the filtered list
//        TypedQuery service = em.createQuery("SELECT m.movieName, m.releaseDate FROM Memoir m WHERE m.movieName = :movieName", Object[].class);
//        //A container to hold the wanted result
//        List<Object[]> resultList = new ArrayList<Object[]>();
//        //Run service to select row
//        for(Object[] row : filteredList){
//            service.setParameter("movieName", (String)row[0]);
//            resultList.addAll(service.getResultList());
//        }
//
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for(Object[] row : resultList){
//            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//            JsonObject resultObject = Json.createObjectBuilder().
//                    add("Movie Name", (String)row[0]).
//                    add("Release Date", df.format(row[1])).build();
//            arrayBuilder.add(resultObject);
//        }
//
//        JsonArray resultArray = arrayBuilder.build();
//
//        return resultArray;
//    }
//
//    //id to find top five of movies released in the current year
//    @GetMapping(path = "topFiveMovieOfTheYear/{userId}")
//    @Produces({"application/json"})
//    public Object topFiveMovieOfTheYear(@PathVariable("userId") Integer userId){
//        TypedQuery service = em.createQuery("SELECT m.movieName, m.releaseDate, m.rating FROM Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.releaseDate) = FUNC('YEAR', :year) ORDER BY m.rating DESC", Object[].class);
//        service.setParameter("userId", userId);
//        //Current year of the system
//        Calendar now = Calendar.getInstance();
//        int container = now.get(Calendar.YEAR);
//        String year = String.valueOf(container);
//        try {
//            Date convertedYear = new SimpleDateFormat("yyyy").parse(year);
//            service.setParameter("year", convertedYear);
//        } catch (ParseException ex) {
//            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        List<Object[]> resultList= service.getResultList();
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        if(resultList.size() <= 5){
//            for(Object[] row : resultList){
//                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                JsonObject resultObject = Json.createObjectBuilder().
//                        add("Movie Name", (String)row[0]).
//                        add("Release Date", dateFormat.format(row[1])).
//                        add("Rating", (String)row[2]).build();
//                arrayBuilder.add(resultObject);
//            }
//        }else{
//            for(int i = 0; i < 5; i++){
//                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                Object[] row = resultList.get(i);
//                JsonObject resultObject = Json.createObjectBuilder().
//                        add("Movie Name", (String)row[0]).
//                        add("Release Date", dateFormat.format(row[1])).
//                        add("Rating", (String)row[2]).build();
//                arrayBuilder.add(resultObject);
//            }
//        }
//
//        JsonArray resultArray = arrayBuilder.build();
//        return resultArray;
//    }
}
