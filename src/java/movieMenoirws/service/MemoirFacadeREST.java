/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieMenoirws.service;

import java.lang.Short;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import movieMenoirws.Memoir;

/**
 *
 * @author Adrian
 */
@Stateless
@Path("moviemenoirws.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoir entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Memoir find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    //Get by name
    @GET
    @Path("findByMovieName/{movieName}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieName(@PathParam("movieName") String movieName){
        Query query = em.createNamedQuery("Memoir.findByMovieName");
        query.setParameter("movieName", movieName);
        return query.getResultList();
    }
    
    //Get by release date
    @GET
    @Path("findByReleaseDate/{releaseDate}")
    @Produces({"application/json"})
    public List<Memoir> findByReleaseDate(@PathParam("releaseDate") String releaseDate){
        Query query = em.createNamedQuery("Memoir.findByReleaseDate");
        try {
            Date convertedReleaseDate = new SimpleDateFormat("dd-MM-yyyy").parse(releaseDate);
            query.setParameter("releaseDate", convertedReleaseDate);
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return query.getResultList();
    }
    
    //Get by Watch time
    @GET
    @Path("findByWatchTime/{watchTime}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchTime(@PathParam("watchTime") String watchTime){
        Query query = em.createNamedQuery("Memoir.findByWatchTime");
        
        try {
            Date convertedWatchTime = new SimpleDateFormat("HH:mm").parse(watchTime);
            query.setParameter("watchTime", convertedWatchTime);
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return query.getResultList();
    }
    
    //Get by Watch date
    @GET
    @Path("findByWatchDate/{watchDate}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchDate(@PathParam("watchDate") String watchDate){
        Query query = em.createNamedQuery("Memoir.findByWatchDate");
        
        try {
            Date convertedWatchDate = new SimpleDateFormat("dd-MM-yyyy").parse(watchDate);
            query.setParameter("watchDate", convertedWatchDate);
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return query.getResultList();
    }
    
    //Get by comment
    @GET
    @Path("findByComment/{comment}")
    @Produces({"application/json"})
    public List<Memoir> findByComment(@PathParam("comment") String comment){
        Query query = em.createNamedQuery("Memoir.findByComment");
        query.setParameter("comment", comment);
        return query.getResultList();
    }
    
    //Get by rating
    @GET
    @Path("findByRating/{rating}")
    @Produces({"application/json"})
    public List<Memoir> findByRating(@PathParam("rating") String rating){
        Query query = em.createNamedQuery("Memoir.findByRating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }
    
    //Get by userID
    @GET
    @Path("findByUID/{userID}")
    @Produces({"application/json"})
    public List<Memoir> findByUID(@PathParam("userID") int userID){
        TypedQuery query = em.createQuery("SELECT m FROM Memoir m WHERE m.userId.userId = :userID", Memoir.class);
        query.setParameter("userID", userID);
        return query.getResultList();
    }
    
    //Get by movie name and cinema name (Dynamic)
    @GET
    @Path("findByMovieNameAndCinema/{movieName}/{cinemaName}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieNameAndCinema(@PathParam("movieName") String movieName,
                                                 @PathParam("cinemaName") String cinemaName){
        TypedQuery query = em.createQuery("SELECT m FROM Memoir m WHERE UPPER(m.movieName) = UPPER(:movieName) AND UPPER(m.cinemaId.cinemaName) = UPPER(:cinemaName)", Memoir.class);
        query.setParameter("movieName", movieName);
        query.setParameter("cinemaName", cinemaName);
        return query.getResultList();
    }
    
    //Get by movie name and cinema postcode (Static)
    @GET
    @Path("findByMovieNameAndCinemaSuburb/{movieName}/{suburb}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieNameAndCinemaSuburb(@PathParam("movieName") String movieName, 
                                                         @PathParam("suburb") String suburb){
        Query query = em.createNamedQuery("Memoir.findByMovieNameAndCinemaSuburb");
        query.setParameter("movieName", movieName);
        query.setParameter("suburb", suburb);
        return query.getResultList();
    }
    
    //id+start+enddate to find number of entery per postcode
    @GET
    @Path("findByIdDate/{userId}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public Object findByIdDate(@PathParam("userId") Integer userId, 
                                     @PathParam("startDate") String startDate, 
                                     @PathParam("endDate") String endDate){
        TypedQuery query = em.createQuery("SELECT m.cinemaId.suburb, COUNT(m) "
                                                 + "FROM Memoir m "
                                                 + "WHERE m.userId.userId = :userId "
                                                 + "AND m.watchDate BETWEEN :startDate AND :endDate "
                                                 + "GROUP BY m.cinemaId.suburb", Object[].class);
        query.setParameter("userId", userId);
        try {
            Date convertedStartDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            Date convertedEndDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
            query.setParameter("startDate", convertedStartDate);
            query.setParameter("endDate", convertedEndDate);  
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Object[]> resultList= query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row: resultList){
            JsonObject resultObject = Json.createObjectBuilder().add("Suburb", (String)row[0]).add("Count", (Long)row[1]).build();
            arrayBuilder.add(resultObject);
        }
        JsonArray resultArray = arrayBuilder.build();
        System.out.print(resultArray.size());
        return resultArray;
    }
    
    //id+year to find number of entery per month
    @GET
    @Path("countMoviePerMonth/{userId}/{year}")
    @Produces({"application/json"})
    public Object countMoviePerMonth(@PathParam("userId") Integer userId, @PathParam("year") String year){
        TypedQuery query = em.createQuery("SELECT m.watchDate FROM Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.watchDate) = FUNC('YEAR', :year) ", Object[].class);
        query.setParameter("userId", userId);
        try {
            Date convertedYear = new SimpleDateFormat("yyyy").parse(year);
            query.setParameter("year", convertedYear);
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Array of month name
        String[] monthName = new String[13];
        monthName[1] = "Jan";
        monthName[2] = "Feb";
        monthName[3] = "Mar";
        monthName[4] = "Apr";
        monthName[5] = "May";
        monthName[6] = "Jun";
        monthName[7] = "Jul";
        monthName[8] = "Aug";
        monthName[9] = "Sep";
        monthName[10] = "Oct";
        monthName[11] = "Nov";
        monthName[12] = "Dec";
        
        //Array for counting entries
        int[] counter = new int[13];
        Arrays.fill(counter, 0);
        
        List<Object[]> resultList= query.getResultList();
        for(int i = 0; i < resultList.size(); i++){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String thisDate = dateFormat.format(resultList.get(i));
            int thisMonth = Integer.parseInt(thisDate.split("-")[1]);
            counter[thisMonth] += 1;         
        }
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();        
        for (int i = 1; i < monthName.length; i++){  
            JsonObject resultObject = Json.createObjectBuilder().add("Month", monthName[i]).add("Count", counter[i]).build();
            arrayBuilder.add(resultObject);
        }
        JsonArray resultArray = arrayBuilder.build();
        return resultArray;
    }
    
    //id to find highest rating
    @GET
    @Path("findHighestRatingbyUserid/{userId}")
    @Produces({"application/json"})
    public Object findHighestRatingbyUserid(@PathParam("userId") Integer userId){
        TypedQuery query = em.createQuery("SELECT m.movieName, m.rating, m.releaseDate FROM Memoir m WHERE m.userId.userId = :userId", Object[].class);
        query.setParameter("userId", userId);
        
        List<Object[]> resultList = query.getResultList();
        
        short highest = 0;
        for (Object[] row: resultList){
            if((short)row[1] > highest){
                highest = (short)row[1];
            }
            if(highest == 5){
                break;
            }
        }
                     
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row: resultList){
            if((short)row[1] == highest){
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                JsonObject resultObject = Json.createObjectBuilder().
                                                add("Movie", (String)row[0]).
                                                add("rating", (String)row[1]).
                                                add("Release Date", dateFormat.format(row[2])).build();
                arrayBuilder.add(resultObject);
            }
        }
        JsonArray resultArray = arrayBuilder.build();
        return resultArray;
    }
    
    //id to find movies watched in the same year as the release year
    @GET
    @Path("findWatchedSameAsReleaseYear/{userId}")
    @Produces({"application/json"})
    public Object findWatchedSameAsReleaseYear(@PathParam("userId") Integer userId){
          TypedQuery query = em.createQuery("SELECT m.movieName, m.releaseDate from Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.releaseDate) = FUNC('YEAR', m.watchDate)", Object[].class);
          query.setParameter("userId", userId);
          List<Object[]> resultList = query.getResultList();
          JsonArrayBuilder arrayBuilder =  Json.createArrayBuilder();
          for(Object[] row : resultList){
              DateFormat dateFormat = new SimpleDateFormat("yyyy");
              JsonObject resultObject = Json.createObjectBuilder().
                                        add("Movie name", (String)row[0]).
                                        add("Release Date", dateFormat.format(row[1])).build();
              arrayBuilder.add(resultObject);
          }
          JsonArray resultArray = arrayBuilder.build();
          return resultArray;
         
    }
    
    //id to find remake movie
    @GET
    @Path("findRemakebyUserID/{userId}")
    @Produces({"application/json"})
    public Object findRemakebyUserID(@PathParam("userId") Integer userId){
        //first filter out the repeated name with different release date
        TypedQuery filter = em.createQuery("SELECT m.movieName, COUNT(DISTINCT m.releaseDate) FROM Memoir m WHERE m.userId.userId = :userId GROUP BY m.movieName HAVING COUNT(DISTINCT m.releaseDate) > 1", Object[].class);
        filter.setParameter("userId", userId);
        List<Object[]> filteredList = filter.getResultList();
        
        //Select movie name and release date from the filtered list
        TypedQuery query = em.createQuery("SELECT m.movieName, m.releaseDate FROM Memoir m WHERE m.movieName = :movieName", Object[].class);
        //A container to hold the wanted result
        List<Object[]> resultList = new ArrayList<Object[]>();
        //Run query to select row
        for(Object[] row : filteredList){
            query.setParameter("movieName", (String)row[0]);
            resultList.addAll(query.getResultList());
        }
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row : resultList){
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            JsonObject resultObject = Json.createObjectBuilder().
                                            add("Movie Name", (String)row[0]).
                                            add("Release Date", df.format(row[1])).build();
            arrayBuilder.add(resultObject);
        }
        
        JsonArray resultArray = arrayBuilder.build();
                
        return resultArray;
    }
    
    //id to find top five of movies released in the current year
    @GET
    @Path("topFiveMovieOfTheYear/{userId}")
    @Produces({"application/json"})
    public Object topFiveMovieOfTheYear(@PathParam("userId") Integer userId){
        TypedQuery query = em.createQuery("SELECT m.movieName, m.releaseDate, m.rating FROM Memoir m WHERE m.userId.userId = :userId AND FUNC('YEAR', m.releaseDate) = FUNC('YEAR', :year) ORDER BY m.rating DESC", Object[].class);
        query.setParameter("userId", userId);
        //Current year of the system
        Calendar now = Calendar.getInstance();
        int container = now.get(Calendar.YEAR);
        String year = String.valueOf(container);
        try {
            Date convertedYear = new SimpleDateFormat("yyyy").parse(year);
            query.setParameter("year", convertedYear);
        } catch (ParseException ex) {
            Logger.getLogger(MemoirFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Object[]> resultList= query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        if(resultList.size() <= 5){
            for(Object[] row : resultList){
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                JsonObject resultObject = Json.createObjectBuilder().
                                                add("Movie Name", (String)row[0]).
                                                add("Release Date", dateFormat.format(row[1])).
                                                add("Rating", (String)row[2]).build();
                arrayBuilder.add(resultObject);
            }
        }else{
            for(int i = 0; i < 5; i++){
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Object[] row = resultList.get(i);
                JsonObject resultObject = Json.createObjectBuilder().
                                                add("Movie Name", (String)row[0]).
                                                add("Release Date", dateFormat.format(row[1])).
                                                add("Rating", (String)row[2]).build();
                arrayBuilder.add(resultObject);
            }
        }
        
        JsonArray resultArray = arrayBuilder.build();
        return resultArray;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
