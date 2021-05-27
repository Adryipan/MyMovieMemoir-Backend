package com.adrian.mymoviememoirbackend.memoir;

import com.adrian.mymoviememoirbackend.Person.Person;
import com.adrian.mymoviememoirbackend.Person.PersonService;
import com.adrian.mymoviememoirbackend.cinema.Cinema;
import com.adrian.mymoviememoirbackend.cinema.CinemaController;
import com.adrian.mymoviememoirbackend.cinema.CinemaService;
import com.adrian.mymoviememoirbackend.statics.MemoirPostcodeCount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemoirController.class)
class MemoirControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemoirService memoirService;

    @MockBean
    private CinemaService cinemaService;

    @MockBean
    private PersonService personService;

    private final String BASE_URL = "/api/v1/memoir";

    @Test
    void findByMovieNameAndCinemaPostcode() throws Exception {
        final String movieName = "Movie 1 3140";
        final String postcode = "3140";
        List<Memoir> memoirList = new ArrayList<>();
        memoirList.add(new Memoir(
                "Movie 1 3140",
                LocalDate.of(2019, Month.MARCH, 2),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                "Good",
                5f,
                new Cinema(1, "Hoyts", "3140"),
                new Person(1, "John",
                        "Smith",
                        "M",
                        LocalDate.of(1995, Month.MARCH, 29),
                        "16 Coane Street",
                        "VIC",
                        3166)
        ));

        when(memoirService.findByMovieNameAndCinemaPostcode(movieName, "3140")).thenReturn(memoirList);

        final String REQUEST_URL = BASE_URL + "/findByMovieNameAndCinemaPostcode/" + movieName.replace(" ", "-") + "/" + postcode;
        MvcResult result = this.mockMvc.perform(get(REQUEST_URL))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray resultArray = new JSONArray(result.getResponse().getContentAsString());
        for(int i = 0; i < resultArray.length(); i++) {
            assertEquals(movieName, resultArray.getJSONObject(i).getString("movieName"));
        }
    }

    @Test
    void findByMovieNameAndInvalidCinemaPostcode() throws Exception {
        final String movieName = "Movie 1 3140";
        final String postcode = "140";
        List<Memoir> memoirList = new ArrayList<>();
        memoirList.add(new Memoir(
                "Movie 1 3140",
                LocalDate.of(2019, Month.MARCH, 2),
                LocalDateTime.of(LocalDate.now().getYear(), Month.MAY, 2, 12, 12, 12),
                "Good",
                5f,
                new Cinema(1, "Hoyts", "3140"),
                new Person(1, "John",
                        "Smith",
                        "M",
                        LocalDate.of(1995, Month.MARCH, 29),
                        "16 Coane Street",
                        "VIC",
                        3166)
        ));

        when(memoirService.findByMovieNameAndCinemaPostcode(movieName, "3140")).thenReturn(memoirList);

        final String REQUEST_URL = BASE_URL + "/findByMovieNameAndCinemaPostcode/" + movieName.replace(" ", "-") + "/" + postcode;
        MvcResult result = this.mockMvc.perform(get(REQUEST_URL)).andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        JsonObject content = new Gson().fromJson(result.getResponse().getContentAsString(), JsonObject.class);
        assertEquals("\"Invalid Postcode\"", content.get("Message").toString());

    }

    @Test
    void countByIdInvalidDatePerPostcode() throws Exception {
        final String userId = "1";
        final String startDate = "20200101";
        final String endDate = "2021-12-31";
        List<MemoirPostcodeCount> resultList = new ArrayList<>();
        resultList.add(new MemoirPostcodeCount("3140", 3));

        when(memoirService.countByIdDatePerPostcode(1, "2020-01-01", "2021-12-31")).thenReturn(resultList);

        final String REQUEST_URL = BASE_URL + "/countByIdDatePerPostcode/" + userId + "/" + startDate + "/" + endDate;
        MvcResult result = this.mockMvc.perform(get(REQUEST_URL)).andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        JsonObject content = new Gson().fromJson(result.getResponse().getContentAsString(), JsonObject.class);
        assertEquals("\"Incorrect start date format; Please follow format yyyy-mm-dd in numbers.\"", content.get("Message").toString());
    }
    
}