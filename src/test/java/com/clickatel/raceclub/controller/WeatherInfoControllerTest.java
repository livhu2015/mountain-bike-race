package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.model.WeatherInfo;
import com.clickatel.raceclub.service.WeatherInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WeatherInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherInfoService weatherInfoService;

    @Test
    public void shouldReturnWeatherForecast() throws Exception {
        // given
        String location = "New York";
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setDescription("cloudy");
        weatherInfo.setLocation(location);

        given(weatherInfoService.weatherForecast(location)).willReturn(weatherInfo);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{location}/forecast", location))
                .andExpect(status().isOk());

    }

}
