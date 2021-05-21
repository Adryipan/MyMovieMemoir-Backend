package com.adrian.mymoviememoirbackend.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE UPPER(f_name) = UPPER(:fName)")
    Optional<List<Person>> findByFirstName(String fName);

    @Query("SELECT p FROM Person p WHERE UPPER(surname) = UPPER(:surname)")
    Optional<List<Person>> findBySurname(String surname);

    @Query("SELECT p FROM Person p WHERE UPPER(state_code) = UPPER(:stateCode)")
    Optional<List<Person>> findByStateCode(String stateCode);

    @Query("SELECT p FROM Person p WHERE street_address like :streetAddress")
    Optional<List<Person>> findByStreetAddress(String streetAddress);

    @Query("SELECT p FROM Person p WHERE UPPER(gender) = UPPER(:gender)")
    Optional<List<Person>> findByGender(String gender);

    @Query("SELECT p FROM Person p WHERE dob = :dob")
    Optional<List<Person>> findByDob(LocalDate dob);

    @Query("SELECT p FROM Person p WHERE postcode = :postcode")
    Optional<List<Person>> findByPostcode(int postcode);

    @Query("SELECT p FROM Person p WHERE UPPER(f_name) = UPPER(:firstName) AND UPPER(surname) = UPPER(:surname) AND postcode = :postcode ")
    Optional<Person> findByFullNamePostcode(String firstName, String surname, int postcode);


}
