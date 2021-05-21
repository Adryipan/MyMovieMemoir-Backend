package com.adrian.mymoviememoirbackend.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("findByFullNamePostcode/{firstName}/{surname}/{postcode}")
    public List<Person> findByFullNamePostcode(
            @PathVariable("firstName") String firstName,
            @PathVariable("surname") String surname,
            @PathVariable("postcode") int postcode) {

        return personService.findByFullNamePostcode(firstName, surname, postcode);
    }







}
