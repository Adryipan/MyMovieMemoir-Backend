package com.adrian.mymoviememoirbackend.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Person findById(long id) {
        Optional<Person> user = personRepository.findById(id);
        if(!user.isPresent()){
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        return user.get();
    }

    public List<Person> findByFirstName(String fname) {
        Optional<List<Person>> users = personRepository.findByFirstName(fname);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with fist name " + fname + " does not exist.");
        }
//        else if(users.get().size() == 0){
//            throw new IllegalStateException("User with fist name " + fname + " does not exist.");
//        }
        return users.get();
    }

    public List<Person> findBySurname(String surname) {
        Optional<List<Person>> users = personRepository.findBySurname(surname);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with surname " + surname + " does not exist.");
        }
        return users.get();
    }

    public List<Person> findByStateCode(String stateCode) {
        Optional<List<Person>> users = personRepository.findByStateCode(stateCode);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with state code " + stateCode + " does not exist.");
        }
        return users.get();
    }

    public List<Person> findByStreetAddress(String streetAddress) {
        Optional<List<Person>> users = personRepository.findByStreetAddress(streetAddress);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with address " + streetAddress + " does not exist.");
        }
        return users.get();
    }

    public List<Person> findByGender(String gender) {
        Optional<List<Person>> users = personRepository.findByGender(gender);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with gender " + gender + " does not exist.");
        }
        return users.get();
    }

    public List<Person> findByDob(String dob) {
        String[] dobInt = dob.split("-");
        Optional<List<Person>> users = personRepository.findByDob(LocalDate.of(Integer.parseInt(dobInt[0]), Integer.parseInt(dobInt[1]), Integer.parseInt(dobInt[2])));
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with gender " + dob + " does not exist.");
        }
        return users.get();
    }

    public Person findByFullNamePostcode(String firstName, String surname, int postcode) {
        Optional<Person> users = personRepository.findByFullNamePostcode(firstName, surname, postcode);
        if(!users.isPresent()){
            throw new IllegalStateException("User " + firstName + " " + surname + " living in area " + postcode + " does not exist.");
        }
        return users.get();
    }

    public List<Person> findByPostcode(int postcode) {
        Optional<List<Person>> users = personRepository.findByPostcode(postcode);
        if(!users.isPresent() || users.get().size() == 0){
            throw new IllegalStateException("User with postcode " + postcode + " does not exist.");
        }
        return users.get();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}
