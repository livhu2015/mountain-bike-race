package com.clickatel.raceclub.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.clickatel.raceclub.model.WeatherInfo;
import com.clickatel.raceclub.service.impl.WeatherInfoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource(properties = {"weather.info.api.key=test-api-key"})
public class WeatherInfoServiceTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherInfoServiceImpl weatherInfoService;
    @Test
    void testWeatherForecast() throws JsonProcessingException {
        String mockResponse = "{\"name\":\"Paris\",\"weather\":[{\"description\":\"light rain\"}],\"main\":{\"temp\":12.5,\"humidity\":87.0},\"wind\":{\"speed\":3.6}}";
        JsonNode mockJson = new ObjectMapper().readTree(mockResponse);

        String url = "https://api.openweathermap.org/data/2.5";

        when(restTemplate.getForObject(url, String.class)).thenReturn(mockResponse);

        WeatherInfo weatherInfo = weatherInfoService.weatherForecast("Paris");

        assertEquals("Paris", weatherInfo.getLocation());
        assertEquals("light rain", weatherInfo.getDescription());
        assertEquals(12.5, weatherInfo.getTemperature());
        assertEquals(87.0, weatherInfo.getHumidity());
        assertEquals(3.6, weatherInfo.getWindSpeed());
    }
}
