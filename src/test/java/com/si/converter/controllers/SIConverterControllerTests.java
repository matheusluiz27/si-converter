package com.si.converter.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SIConverterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successResponseTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/units/si?units=tonne"))
                .andExpect(status().isOk());

        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        JSONObject responseJson = new JSONObject(responseString);

        Assertions.assertTrue(responseJson.has("unit_name"));
        Assertions.assertTrue(responseJson.has("multiplication_factor"));
        Assertions.assertEquals("kg", responseJson.get("unit_name"));
        Assertions.assertEquals(new BigDecimal(1000), new BigDecimal(responseJson.get("multiplication_factor").toString()));
    }

    @Test
    void errorResponseWithInvalidUnitsTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/units/si?units=blabla"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Invalid units", result.getResolvedException().getMessage()));
    }
}
