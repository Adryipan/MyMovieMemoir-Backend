/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieMenoirws.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
import movieMenoirws.Person;

/**
 *
 * @author Adrian
 */
@Stateless
@Path("moviemenoirws.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
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
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //get by fname
    @GET
    @Path("findByFirstName/{fName}")
    @Produces({"application/json"})
    public List<Person> findByFirstName(@PathParam("fName") String fName){
        Query query = em.createNamedQuery("Person.findByFirstName");
        query.setParameter("firstName", fName);
        return query.getResultList();
    }
    
    //get by surname
    @GET
    @Path("findBySurname/{surname}")
    @Produces({"application/json"})
    public List<Person> findBySurname(@PathParam("surname") String surname){
        Query query = em.createNamedQuery("Person.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    //get by gender
    @GET
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
    public List<Person> findByGender(@PathParam("gender") String gender){
        Query query = em.createNamedQuery("Person.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
        
    //get by dob
    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
    public List<Person> findByDob(@PathParam("dob") String dob){
        Query query = em.createNamedQuery("Person.findByDob");
        
        try {
            Date convertedDob = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
            query.setParameter("dob", convertedDob);
        } catch (ParseException ex) {
            Logger.getLogger(PersonFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return query.getResultList();
    }
    
    //get by streetNumber
    @GET
    @Path("findByStreetNumber/{streetNumber}")
    @Produces({"application/json"})
    public List<Person> findByStreetNumber(@PathParam("streetNumber") String streetNumber){
        Query query = em.createNamedQuery("Person.findByStreetNumber");
        query.setParameter("streetNumber", streetNumber);
        return query.getResultList();
    }
    
    //get by streetName
    @GET
    @Path("findByStreetName/{streetName}")
    @Produces({"application/json"})
    public List<Person> findByStreetName(@PathParam("streetName") String streetName){
        Query query = em.createNamedQuery("Person.findByStreetName");
        query.setParameter("streetName", streetName);
        return query.getResultList();
    }
    
    //get by stateCode
    @GET
    @Path("findByStateCode/{stateCode}")
    @Produces({"application/json"})
    public List<Person> findByStateCode(@PathParam("stateCode") String stateCode){
        Query query = em.createNamedQuery("Person.findByStateCode");
        query.setParameter("stateCode", stateCode);
        return query.getResultList();
    }
    
    //get by postcode
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Person> findByPostcode(@PathParam("postcode") String postcode){
        Query query = em.createNamedQuery("Person.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    //First name, surname and postcode
    @GET
    @Path("findByFullNamePostcode/{firstName}/{surname}/{postcode}")
    @Produces({"application/json"})
    public List<Person> findByFullNamePostcode(@PathParam("firstName") String firstName, 
                                                @PathParam("surname") String surname,
                                                @PathParam("postcode") String postcode){
        TypedQuery<Person> query = em.createQuery("SELECT P FROM Person p WHERE UPPER(p.firstName)=UPPER(:firstName) AND UPPER(p.surname) = UPPER(:surname) AND p.postcode = :postcode", Person.class);
        query.setParameter("firstName", firstName);
        query.setParameter("surname", surname);
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
