package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RaceResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RaceResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceResultService raceResultService;

    @Test
    public void testGetFastestThreeRidersByRace() throws Exception {
        long raceId = 1L;
        Rider rider1 = Rider.builder().name("John Doe").age(25).build();
        Rider rider2 = Rider.builder().name("Jane Smith").age(27).build();
        Rider rider3 = Rider.builder().name("Bob Johnson").age(30).build();

        List<Rider> riders = Arrays.asList(rider1, rider2, rider3);

        when(raceResultService.getFastestThreeRidersByRace(raceId)).thenReturn(riders);

        mockMvc.perform(MockMvcRequestBuilders.get("/race-results/" + raceId + "/fastest-three-riders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("John Doe")))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is("Jane Smith")))
                .andExpect((ResultMatcher) jsonPath("$[2].name", is("Bob Johnson")));

    }

    @Test
    public void testGetRidersDidNotFinish() throws Exception {
        long raceId = 1L;
        Rider rider1 = Rider.builder().name("John Doe").age(25).build();
        Rider rider2 = Rider.builder().name("Jane Smith").age(25).build();
        List<Rider> riders = Arrays.asList(rider1, rider2);

        when(raceResultService.getRidersDidNotFinish(raceId)).thenReturn(riders);

        mockMvc.perform(MockMvcRequestBuilders.get("/race-results/" + raceId + "/riders-did-not-finish"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("John Doe")))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is("Jane Smith")));

    }

    @Test
    public void testGetRidersDidNotTakePartInRace() throws Exception {
        long raceId = 1L;
        Rider rider1 = Rider.builder().name("John Doe").age(25).build();
        Rider rider2 = Rider.builder().name("Jane Smith").age(25).build();
        List<Rider> riders = Arrays.asList(rider1, rider2);
        when(raceResultService.getRidersDidNotTakePartInRace(raceId)).thenReturn(riders);

        mockMvc.perform(MockMvcRequestBuilders.get("/race-results/" + raceId + "/riders-did-not-take-part"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("John Doe")))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is("Jane Smith")));

    }
}
