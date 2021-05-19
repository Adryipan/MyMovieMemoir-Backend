package com.adrian.mymoviememoirbackend.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Person getPersonById(@PathVariable("userId") long id){
        return personService.getPersonById(id);
    }

    @GetMapping(path = "findByFirstName/{fname}")
    public List<Person> getPersonByFirstName(@PathVariable("fname") String fname){
        return personService.getPersonByFirstName(fname);
    }
}
