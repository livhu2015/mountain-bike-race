package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RiderDto;
import com.clickatel.raceclub.model.Rider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RiderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private RiderController clientController;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetRiderById() throws Exception {
        RiderDto rider = RiderDto.builder().name("Alice").age(32).build();

        clientController.createRider(rider);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/rider/riders/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String responseJson = result.getResponse().getContentAsString();
        Rider responseRider = objectMapper.readValue(responseJson, Rider.class);
        assertEquals(rider.getName(), responseRider.getName());
        assertEquals(rider.getAge(), responseRider.getAge());
    }

    @Test
    public void testGetRiders() throws Exception {
        ArrayList<RiderDto> riders = new ArrayList<>();
        RiderDto rider1 = RiderDto.builder().name("Alice").age(32).build();
        RiderDto rider2 = RiderDto.builder().name("Bob").age(23).build();

        riders.add(rider1);
        riders.add(rider2);
        for (RiderDto r: riders) {
            clientController.createRider(r);
        }

        // Perform a GET request to the /riders endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/rider/all/riders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verify that the response body contains the expected JSON data
        String expectedJson = "[{\"name\":\"Alice\",\"age\":\"32\"}, {\"name\":\"Bob\",\"age\":\"23\"}]";
        String actualJson = result.getResponse().getContentAsString();

        // Verify that the response body contains the expected JSON data
        List<Rider> responseRider = objectMapper.readValue(expectedJson, ArrayList.class);
        assertEquals(riders.size(), responseRider.size());

    }

    @Test
    public void testCreateRider() throws Exception {
        // Define the request body
        RiderDto rider = RiderDto.builder().name("Alice").age(32).build();
        String requestBody = objectMapper.writeValueAsString(rider);

        // Perform a POST request to the /riders endpoint with the request body
        mockMvc.perform(MockMvcRequestBuilders.post("/rider/create/rider")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Rider> riders = clientController.getRiders();
        assertThat(riders.size(), equalTo(1));
        assertThat(riders.get(0).getName(), equalTo("Alice"));

    }

}
