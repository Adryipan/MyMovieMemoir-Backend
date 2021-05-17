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
import movieMenoirws.Credentials;

/**
 *
 * @author Adrian
 */
@Stateless
@Path("moviemenoirws.credentials")
public class CredentialsFacadeREST extends AbstractFacade<Credentials> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public CredentialsFacadeREST() {
        super(Credentials.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credentials entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Credentials entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credentials find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentials> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentials> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //Get by authentication
    @GET
    @Path("findByAuthentication/{username}/{password}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findByAuthentication(@PathParam("username") String username, @PathParam("password") String password){
        TypedQuery query = em.createQuery("SELECT c.username, c.userId.userId FROM Credentials c WHERE c.username = :username AND c.password = :password", Object[].class );
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Object[]> resultList = query.getResultList();
        String result = "";  
        if(resultList.size() == 1){
            for(Object[] row: resultList){
                result = String.valueOf(row[1]);
            }
        }
        return result;
    }
    
    //Get by username
    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<Credentials> findByUsername(@PathParam("username") String username){
        Query query = em.createNamedQuery("Credentials.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
    
    //Get by password
    @GET
    @Path("findByPassword/{password}")
    @Produces({"application/json"})
    public List<Credentials> findByPassword(@PathParam("password") String password){
        Query query = em.createNamedQuery("Credentials.findByPassword");
        query.setParameter("password", password);
        return query.getResultList();
    }
    
    //Get by signup date
    @GET
    @Path("findBySignUpDate/{signUpDate}")
    @Produces({"application/json"})
    public List<Credentials> findBySignUpDate(@PathParam("signUpDate") String signUpDate){
        Query query = em.createNamedQuery("Credentials.findBySignUpDate");
        try {
            Date convertedDate = new SimpleDateFormat("dd-MM-yyyy").parse(signUpDate);
            query.setParameter("signUpDate", convertedDate);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialsFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
