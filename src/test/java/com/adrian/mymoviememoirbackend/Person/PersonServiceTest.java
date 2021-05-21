package com.adrian.mymoviememoirbackend.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @BeforeEach
    void setUp() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "John",
                "Hughes",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "NSW",
                2291));

        data.add(new Person(
                "Mary",
                "Hughes",
                "F",
                LocalDate.of(1959, Month.SEPTEMBER, 29),
                "42 Colleague Walk",
                "VIC",
                3800));

        data.add(new Person(
                "Karina",
                "Perry",
                "F",
                LocalDate.of(1975, Month.APRIL, 2),
                "151 City Road",
                "VIC",
                3006));

        repository.saveAll(data);
    }

    @Test
    void shouldReturnAllPerson() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "John",
                "Hughes",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "NSW",
                2291));

        data.add(new Person(
                "Mary",
                "Hughes",
                "F",
                LocalDate.of(1959, Month.SEPTEMBER, 29),
                "42 Colleague Walk",
                "VIC",
                3800));

        data.add(new Person(
                "Karina",
                "Perry",
                "F",
                LocalDate.of(1975, Month.APRIL, 2),
                "151 City Road",
                "VIC",
                3006));

        given(repository.findAll()).willReturn(data);

        List<Person> expected = service.getAllPerson();
        assertEquals(expected, data);

    }

    @Test
    void findById() {
        final long id = 1L;
        final Person person = new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166);

        given(repository.findById(1L)).willReturn(Optional.of(person));

        final Person expected = service.findById(id);
        assertThat(expected).isNotNull();

    }

    @Test
    void getPersonByFirstName() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "John",
                "Hughes",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "NSW",
                2291));

        given(repository.findByFirstName("john")).willReturn(Optional.of(data));

        final List<Person> expected = service.findByFirstName("john");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected){
            assertThat(thisPerson.getFirstName()).isEqualTo("John");
        }
    }

    @Test
    void findBySurname() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        given(repository.findBySurname("smith")).willReturn(Optional.of(data));

        final List<Person> expected = service.findBySurname("smith");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected){
            assertThat(thisPerson.getSurname()).isEqualTo("Smith");
        }
    }

    @Test
    void findByStateCode() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "Mary",
                "Hughes",
                "F",
                LocalDate.of(1959, Month.SEPTEMBER, 29),
                "42 Colleague Walk",
                "VIC",
                3800));

        data.add(new Person(
                "Karina",
                "Perry",
                "F",
                LocalDate.of(1975, Month.APRIL, 2),
                "151 City Road",
                "VIC",
                3006));

        given(repository.findByStateCode("VIC")).willReturn(Optional.of(data));

        final List<Person> expected = service.findByStateCode("VIC");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected){
            assertThat(thisPerson.getStateCode()).isEqualTo("VIC");
        }
    }

    @Test
    void findByStreetAddress() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "John",
                "Hughes",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "NSW",
                2291));

        given(repository.findByStreetAddress("16 Coane Street")).willReturn(Optional.of(data));

        final List<Person> expected = service.findByStreetAddress("16 Coane Street");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected){
            assertThat(thisPerson.getStreetAddress()).isEqualTo("16 Coane Street");
        }
    }

    @Test
    void findByGender() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "Mary",
                "Hughes",
                "F",
                LocalDate.of(1959, Month.SEPTEMBER, 29),
                "42 Colleague Walk",
                "VIC",
                3800));

        data.add(new Person(
                "Karina",
                "Perry",
                "F",
                LocalDate.of(1975, Month.APRIL, 2),
                "151 City Road",
                "VIC",
                3006));

        given(repository.findByGender("F")).willReturn(Optional.of(data));

        final List<Person> expected = service.findByGender("F");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected) {
            assertThat(thisPerson.getGender()).isEqualTo("F");
        }
    }

    @Test
    void findByDob() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        data.add(new Person(
                "John",
                "Hughes",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "NSW",
                2291));

        given(repository.findByDob(LocalDate.of(1995, Month.MARCH, 29))).willReturn(Optional.of(data));

        final List<Person> expected = service.findByDob("1995-03-29");
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected) {
            assertThat(thisPerson.getDob()).isEqualTo(LocalDate.of(1995, Month.MARCH, 29));
        }
    }

    @Test
    void findByFullNamePostcode() {
        Person data = new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166);

        given(repository.findByFullNamePostcode("john", "smith", 3166)).willReturn(Optional.of(data));

        final Person expected = service.findByFullNamePostcode("john", "smith", 3166);
        assertThat(expected).isNotNull();
        assertThat(expected.getFirstName()).isEqualTo("John");
        assertThat(expected.getSurname()).isEqualTo("Smith");
        assertThat(expected.getPostcode()).isEqualTo(3166);

    }

    @Test
    void findByPostcode() {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        given(repository.findByPostcode(3166)).willReturn(Optional.of(data));

        final List<Person> expected = service.findByPostcode(3166);
        assertThat(expected).isNotNull();
        for(Person thisPerson : expected) {
            assertThat(thisPerson.getPostcode()).isEqualTo(3166);
        }
    }

    @Test
    void shouldSavePersonSuccessfully(){
        final Person person = new Person(
                "John",
                "Watson",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166);

        given(repository.save(person)).willAnswer(invocation -> invocation.getArgument(0));

        Person persistedPerson = service.save(person);

        assertThat(persistedPerson).isNotNull();
        verify(repository).save(any(Person.class));
    }

    @Test
    void shouldThrowException(){
        Exception exception = assertThrows(IllegalStateException.class, ()->{
            service.findById(5L);
        });

        String expectedMessage = "User with id 5 does not exist.";
        assertTrue(exception.getMessage().contains(expectedMessage));

    }
}