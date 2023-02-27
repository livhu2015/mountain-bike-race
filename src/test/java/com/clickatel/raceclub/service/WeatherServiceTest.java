package com.clickatel.raceclub.service;

import com.clickatel.raceclub.model.WeatherInfo;
import com.clickatel.raceclub.service.impl.WeatherInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    private static String WEATHER_INFO_API_KEY = "7b11cba75150e10caf5c3a4878d1b89d";
    private static String WEATHER_INFO_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherInfoServiceImpl weatherInfoService;

    @Test
    public void testGetWeatherInfo() throws Exception {
        // Construct the URL for the API call
        String location = "New York";
        String apiUrl = WEATHER_INFO_URL + location + "&appid=" + WEATHER_INFO_API_KEY;


        // Create a mock HTTP response with sample weather data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String responseBody = "{\"weather\":[{\"description\":\"Cloudy\"}],\"main\":{\"temp\":12.3,\"humidity\":45.6},\"wind\":{\"speed\":7.8}}";
        HttpEntity<String> responseEntity = new HttpEntity<>(responseBody, headers);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        // Mock the RestTemplate's getForObject method to return the sample weather data
        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(String.valueOf(mockResponse));


        // Call the weather service to retrieve the weather data
        WeatherInfo weatherinfo = weatherInfoService.weatherForecast(location);

        // Verify that the weather data was parsed correctly
        assertEquals("clear sky", weatherinfo.getDescription());
//        assertEquals(12.3, weatherinfo.getTemperature(), 0.001);
//        assertEquals(45.6, weatherinfo.getHumidity(), 0.001);
//        assertEquals(7.8, weatherinfo.getWindSpeed(), 0.001);
    }
}
