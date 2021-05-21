package com.adrian.mymoviememoirbackend.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping(path = "{userId}")
    public Person findById(@PathVariable("userId") long id){
        return personService.findById(id);
    }

    @GetMapping(path = "findByFirstName/{fname}")
    public List<Person> findByFirstName(@PathVariable("fname") String fname){
        return personService.findByFirstName(fname);
    }

    @GetMapping(path = "findBySurname/{surname}")
    public List<Person> findBySurname(@PathVariable("surname") String surname){
        return personService.findBySurname(surname);
    }

    @GetMapping(path = "findByGender/{gender}")
    public List<Person> findByGender(@PathVariable("gender") String gender){
        return personService.findByGender(gender);
    }

    @GetMapping(path = "findByDob/{dob}")
    public List<Person> findByDob(@PathVariable("dob") String dob){
        return personService.findByDob(dob);
    }

    @GetMapping(path = "findByStateCode/{stateCode}")
    public List<Person> findByStateCode(@PathVariable("stateCode") String stateCode){
        return personService.findByStateCode(stateCode);
    }

    @GetMapping(path = "findByStreetAddress/{streetAddress}")
    public List<Person> findByStreetAddress(@PathVariable("streetAddress") String streetAddress){
        return personService.findByStreetAddress(streetAddress.replace("-", " "));
    }

    @GetMapping(path = "findByPostcode/{postcode}")
    public List<Person> findByPostcode(@PathVariable("postcode") int postcode){
        return personService.findByPostcode(postcode);
    }

    @GetMapping(path = "findByFullNamePostcode/{firstName}/{surname}/{postcode}")
    public Person findByFullNamePostcode(
            @PathVariable("firstName") String firstName,
            @PathVariable("surname") String surname,
            @PathVariable("postcode") int postcode) {

        return personService.findByFullNamePostcode(firstName, surname, postcode);
    }

    @PostMapping(
            path = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Person> register(@RequestBody Person person){
        Person persistedPerson = personService.save(person);

        return ResponseEntity.created(
                URI.create(String.format("/register/%s" , person.getFirstName().toLowerCase())))
                .body(persistedPerson);
    }







}
