package com.adrian.mymoviememoirbackend.cinema;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CinemaController.class)
class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaService service;

    private final String BASE_URL = "/api/v1/cinema";

//    @BeforeEach
//    void setUp() {
//    }

    @Test
    void findById() throws Exception {
        Cinema data = new Cinema("Hoyts Chadstone", 3148);

        when(service.findById(0)).thenReturn(data);

        final String requestUrl = BASE_URL + "/0";
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject resultObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(data.getName(), resultObject.getString("name"));
    }

    @Test
    void findByCinemaName() throws Exception {
        final String cinemaName = "Hoyts Chadstone";
        List<Cinema> data = new ArrayList<>();
        data.add(
                new Cinema("Hoyts Chadstone", 3148)
        );


        when(service.findByCinemaName(cinemaName)).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByCinemaName/" + cinemaName.replace(" ", "-");
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray resultArray = new JSONArray(result.getResponse().getContentAsString());
        for(int i = 0; i < resultArray.length(); i++) {
            assertEquals(cinemaName, resultArray.getJSONObject(i).getString("name"));
        }
    }

    @Test
    void findByPostcode() throws Exception {
        final int postcode = 3148;
        List<Cinema> data = new ArrayList<>();
        data.add(
                new Cinema("Hoyts Chadstone", 3148)
        );

        when(service.findByPostcode(postcode)).thenReturn(data);

        final String requestUrl = BASE_URL + "/findByPostcode/" + postcode;
        MvcResult result = this.mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray resultArray = new JSONArray(result.getResponse().getContentAsString());
        for(int i = 0; i < resultArray.length(); i++) {
            assertEquals(postcode, resultArray.getJSONObject(i).getInt("postcode"));
        }
    }
}