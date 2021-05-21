package com.adrian.mymoviememoirbackend.Person;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Mock
    private PersonRepository repository;

    private final String BASE_URL = "/api/v1/person";


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
    void getAllPerson() throws Exception{
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

        when(service.getAllPerson()).thenReturn(data);

        MvcResult result = this.mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        assertEquals(resultArray.length(), data.size());
        for (int i = 0; i < resultArray.length(); i++){
            JSONObject thisResult = resultArray.getJSONObject(i);
            Person expectedPerson = data.get(i);
            assertEquals(thisResult.length(), expectedPerson.getClass().getDeclaredFields().length-2);

            assertEquals(thisResult.getLong("userId"), expectedPerson.getUserId());
            assertEquals(thisResult.getString("firstName"), expectedPerson.getFirstName());
            assertEquals(thisResult.getString("surname"), expectedPerson.getSurname());
        }

    }

    @Test
    void findById() throws Exception {
        Person person = new Person(
        "John",
        "Smith",
        "M",
        LocalDate.of(1995, Month.MARCH, 29),
        "16 Coane Street",
        "VIC",
        3166);

        when(service.findById(1)).thenReturn(person);

        final String requestUrl = BASE_URL + "/1";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONObject resultObject= new JSONObject(resultJson);
        assertEquals(
                person.getClass().getDeclaredFields().length-2,
                resultObject.length());
        assertEquals(0L, resultObject.getLong("userId"));
        assertEquals(person.getFirstName(), resultObject.getString("firstName"));
        assertEquals(person.getSurname(), resultObject.getString("surname"));
    }

    @Test
    void findByFirstName() throws Exception {
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

        when(service.findByFirstName("john")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByFirstName/john";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        for(int i = 0; i < resultArray.length(); i++){
            assertEquals(
                    data.get(i).getClass().getDeclaredFields().length-2,
                    resultArray.getJSONObject(i).length());
            assertEquals(data.get(i).getUserId(), resultArray.getJSONObject(i).getLong("userId"));
        }
    }

    @Test
    void findBySurname() throws Exception {
        List<Person> data = new ArrayList<>();
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

        when(service.findBySurname("Hughes")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findBySurname/Hughes";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        for(int i = 0; i < resultArray.length(); i++){
            assertEquals(
                    data.get(i).getClass().getDeclaredFields().length-2,
                    resultArray.getJSONObject(i).length());
            assertEquals(data.get(i).getUserId(), resultArray.getJSONObject(i).getLong("userId"));
        }
    }

    @Test
    void findByGender() throws Exception {
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

        when(service.findByGender("M")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByGender/M";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        for(int i = 0; i < resultArray.length(); i++){
            assertEquals(
                    data.get(i).getClass().getDeclaredFields().length-2,
                    resultArray.getJSONObject(i).length());
            assertEquals(data.get(i).getUserId(), resultArray.getJSONObject(i).getLong("userId"));
        }
    }

    @Test
    void findByDob() throws Exception {
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

        when(service.findByDob("1995-03-29")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByDob/1995-03-29";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        for(int i = 0; i < resultArray.length(); i++){
            assertEquals(
                    data.get(i).getClass().getDeclaredFields().length-2,
                    resultArray.getJSONObject(i).length());
            assertEquals(data.get(i).getUserId(), resultArray.getJSONObject(i).getLong("userId"));
        }
    }

    @Test
    void findByStateCode() throws Exception {
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

        when(service.findByStateCode("VIC")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByStateCode/VIC";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        assertEquals(resultArray.length(), data.size());
        for (int i = 0; i < resultArray.length(); i++){
            JSONObject thisResult = resultArray.getJSONObject(i);
            assertEquals(thisResult.length(), data.get(i).getClass().getDeclaredFields().length-2);
            assertEquals(thisResult.getLong("userId"), data.get(i).getUserId());
        }
    }

    @Test
    void findByStreetAddress() throws Exception {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        when(service.findByStreetAddress("16 Coane Street")).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByStreetAddress/16-Coane-Street";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        assertEquals(resultArray.length(), data.size());
        for (int i = 0; i < resultArray.length(); i++){
            JSONObject thisResult = resultArray.getJSONObject(i);
            assertEquals(thisResult.length(), data.get(i).getClass().getDeclaredFields().length-2);
            assertEquals(thisResult.getLong("userId"), data.get(i).getUserId());
        }
    }

    @Test
    void findByPostcode() throws Exception {
        List<Person> data = new ArrayList<>();
        data.add(new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166));

        when(service.findByPostcode(3166)).thenReturn(data);
        final String requestUrl = BASE_URL + "/findByPostcode/3166";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();

        JSONArray resultArray = new JSONArray(resultJson);
        for(int i = 0; i < resultArray.length(); i++){
            assertEquals(
                    data.get(i).getClass().getDeclaredFields().length-2,
                    resultArray.getJSONObject(i).length());
            assertEquals(data.get(i).getUserId(), resultArray.getJSONObject(i).getLong("userId"));
        }
    }

    @Test
    void findByFullNamePostcode() throws Exception {
        Person data = new Person(
                "John",
                "Smith",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166);

        when(service.findByFullNamePostcode("john", "smith", 3166)).thenReturn(data);
        final String requestUrl = BASE_URL + "/findByFullNamePostcode/john/smith/3166";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject resultJson = new JSONObject(result.getResponse().getContentAsString());

        assertEquals(
                data.getClass().getDeclaredFields().length-2,
                resultJson.length());
        assertEquals(data.getUserId(), resultJson.getLong("userId"));

    }

    @Test
    void register() throws Exception {
        Person person = new Person(
                "John",
                "Watson",
                "M",
                LocalDate.of(1995, Month.MARCH, 29),
                "16 Coane Street",
                "VIC",
                3166
        );

        given(service.findByFullNamePostcode("john", "watson", 3166)).willThrow(IllegalArgumentException.class);

        when(service.save(any(Person.class))).thenReturn(person);

        final String requestUrl = BASE_URL + "/register";
        this.mockMvc.perform(
                post(requestUrl)
                .content("{\n" +
                        "    \"firstName\": \"John\",\n" +
                        "    \"surname\": \"Watson\",\n" +
                        "    \"gender\": \"M\",\n" +
                        "    \"dob\": \"1995-03-28\",\n" +
                        "    \"streetAddress\": \"16 Coane Street\",\n" +
                        "    \"stateCode\": \"VIC\",\n" +
                        "    \"postcode\": 3166\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isCreated()).andDo(
                    mvcResult -> {
                        JSONObject resultJson = new JSONObject(mvcResult.getResponse().getContentAsString());
                        assertEquals(
                                person.getClass().getDeclaredFields().length-2,
                                resultJson.length());
                        assertEquals(person.getFirstName(), resultJson.getString("firstName"));
                        assertEquals(person.getSurname(), resultJson.getString("surname"));
                        assertEquals(person.getPostcode(), resultJson.getInt("postcode"));
                    }
        );
    }

    @Test
    void shouldReturnEmpty() throws Exception {
        final String requestUrl = BASE_URL + "/5";

        this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}