package com.clickatel.raceclub.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RiderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RiderController clientController;

    @Test
    public void when_rider_controller_is_injected_then_result_NotNull() throws Exception {
        assertThat(clientController).isNotNull();
    }

    @Test
    public void testGetRiderById() throws Exception {
        // Perform a GET request to the /riders/{id} endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/riders/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String expectedJson = "{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@example.com\",\"phone\":\"1234567890\"}";
        String actualJson = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void testGetRiders() throws Exception {
        // Perform a GET request to the /riders endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/all/riders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String expectedJson = "[{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@example.com\",\"phone\":\"1234567890\"}, {\"id\":2,\"name\":\"Bob\",\"email\":\"bob@example.com\",\"phone\":\"2345678901\"}]";
        String actualJson = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void testCreateRider() throws Exception {
        // Define the request body
        String requestBody = "{\"name\":\"Charlie\",\"email\":\"charlie@example.com\",\"phone\":\"3456789012\"}";

        // Perform a POST request to the /riders endpoint with the request body
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/create/riders")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String expectedJson = "{\"id\":3,\"name\":\"Charlie\",\"email\":\"charlie@example.com\",\"phone\":\"3456789012\"}";
        String actualJson = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void testUpdateRider() throws Exception {
        // Define the request body
        String requestBody = "{\"name\":\"Alice\",\"email\":\"alice@example.com\",\"phone\":\"1111111111\"}";

        // Perform a PUT request to the /riders/{id} endpoint with the request body and ID of 1
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/update/riders/{id}", 1L)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String expectedJson = "{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@example.com\",\"phone\":\"1111111111\"}";
        String actualJson = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void testDeleteRider() throws Exception {
        // Perform a DELETE request to the /riders/{id} endpoint with ID of 1
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/riders/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the rider with ID 1 is no longer in the database
        mockMvc.perform(MockMvcRequestBuilders.get("/riders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
}
