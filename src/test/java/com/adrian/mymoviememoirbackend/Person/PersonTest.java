package com.adrian.mymoviememoirbackend.Person;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonTest {

    @Autowired
    private MockMvc mockMvc;

    public final String BASE_URL = "/api/v1/person";

//    @Test
//    public void shouldReturnAllPerson() throws Exception{
//        ResultActions resultActions =  this.mockMvc.perform(get(BASE_URL)).andDo(print()).andExpect(status().isOk());
//
//        this.mockMvc.perform(get(BASE_URL)).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(
//                        content().string(
//                                containsString(
//                                        "[{\"user_id\":1,\"firstName\":\"John\",\"surname\":\"Smith\",\"gender\":\"M\",\"dob\":\"1995-03-28\",\"streetAddress\":\"16 Coane Street\",\"stateCode\":\"VIC\",\"postcode\":3166},{\"user_id\":2,\"firstName\":\"Mary\",\"surname\":\"Hughes\",\"gender\":\"F\",\"dob\":\"1993-04-02\",\"streetAddress\":\"42 Colleague Walk\",\"stateCode\":\"VIC\",\"postcode\":3800},{\"user_id\":3,\"firstName\":\"Karina\",\"surname\":\"Perry\",\"gender\":\"F\",\"dob\":\"1975-09-29\",\"streetAddress\":\"151 City Road\",\"stateCode\":\"VIC\",\"postcode\":3006}]"
//                                )));
//    }
}
