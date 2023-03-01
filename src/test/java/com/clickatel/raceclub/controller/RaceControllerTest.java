package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RaceDto;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.service.RaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;

    @Test
    public void testGetRaceById() throws Exception {
        long raceId = 1L;
        Race race = Race.builder().title("Test Race").id(raceId).country("Test Location").build();

        given(raceService.getRaceById(raceId)).willReturn(race);

        mockMvc.perform(MockMvcRequestBuilders.get("/race/get/{id}", raceId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRaces() throws Exception {
        Race race = Race.builder().title("Test Race").country("Test Location").build();

        List<Race> races = Arrays.asList(race);

        given(raceService.getRaces()).willReturn(races);

        mockMvc.perform(MockMvcRequestBuilders.get("/race/all/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void testCreateRace() throws Exception {
        Race race = Race.builder().title("Test Race").country("Test Location").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/race/create/race")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(race)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteRace() throws Exception {
        long raceId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/race/delete/{id}", raceId))
                .andExpect(status().isOk());

    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
