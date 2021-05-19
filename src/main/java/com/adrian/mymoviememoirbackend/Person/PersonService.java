package com.adrian.mymoviememoirbackend.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Person getPersonById(long id) {
        Optional<Person> user = personRepository.findById(id);
        if(!user.isPresent()){
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        return user.get();
    }

    public List<Person> getPersonByFirstName(String fname) {
        Optional<List<Person>> user = personRepository.findByFirstName(fname);
        if(!user.isPresent()){
            throw new IllegalStateException("User with fname " + fname + " does not exist.");
        }
        return user.get();
    }
}
