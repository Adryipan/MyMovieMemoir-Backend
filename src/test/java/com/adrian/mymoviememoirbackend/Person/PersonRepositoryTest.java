package com.adrian.mymoviememoirbackend.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        this.entityManager.persist(
                new Person(
                        "John",
                        "Smith",
                        "M",
                        LocalDate.of(1995, Month.MARCH, 29),
                        "16 Coane Street",
                        "VIC",
                        3166));

        this.entityManager.persist(
                new Person(
                        "Mary",
                        "Hughes",
                        "F",
                        LocalDate.of(1959, Month.SEPTEMBER, 29),
                        "42 Colleague Walk",
                        "VIC",
                        3800));

        this.entityManager.persist(
                new Person(
                        "Karina",
                        "Perry",
                        "F",
                        LocalDate.of(1975, Month.APRIL, 2),
                        "151 City Road",
                        "VIC",
                        3006));
    }

    @Test
    void findByFirstName() throws Exception{
        Optional<List<Person>> result = this.repository.findByFirstName("john");
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        assertThat(person.get(0).getFirstName()).isEqualTo("John");
    }

    @Test
    void findBySurname() {
        Optional<List<Person>> result = this.repository.findBySurname("smith");
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        assertThat(person.get(0).getSurname()).isEqualTo("Smith");
    }

    @Test
    void findByPostcode() {
        Optional<List<Person>> result = this.repository.findByPostcode(3800);
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        assertThat(person.get(0).getSurname()).isEqualTo("Hughes");
    }

    @Test
    void findByStateCode() {
        Optional<List<Person>> result = this.repository.findByStateCode("VIC");
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        for(Person thisPerson : person){
            assertThat(thisPerson.getStateCode()).isEqualTo("VIC");
        }
    }

    @Test
    void findByStreetAddress() {
        Optional<List<Person>> result = this.repository.findByStreetAddress("151 City Road");
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0 );
        for(Person thisPerson : person){
            assertThat(thisPerson.getStreetAddress()).isEqualTo("151 City Road");
        }
    }

    @Test
    void findByGender() {
        Optional<List<Person>> result = this.repository.findByGender("F");
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        for(Person thisPerson : person){
            assertThat(thisPerson.getGender()).isEqualTo("F");
        }
    }

    @Test
    void findByDob() {
        Optional<List<Person>> result = this.repository.findByDob(LocalDate.of(1995, Month.MARCH, 29));
        assertThat(result.isPresent()).isTrue();

        List<Person> person = result.get();
        assertTrue(person.size() > 0);
        for(Person thisPerson : person){
            assertThat(thisPerson.getDob()).isEqualTo(LocalDate.of(1995, Month.MARCH, 29));
        }
    }

    @Test
    void findByFullNamePostcode() {
        Optional<Person> result = this.repository.findByFullNamePostcode("john", "smith", 3166);
        assertThat(result.isPresent()).isTrue();

        Person person = result.get();
        assertThat(person.getFirstName()).isEqualTo("John");
        assertThat(person.getSurname()).isEqualTo("Smith");
        assertThat(person.getPostcode()).isEqualTo(3166);
    }

    @Test
    void shouldReturnEmpty(){
        Optional<Person> result = this.repository.findById(5L);
        assertTrue(result.isEmpty());
    }

}