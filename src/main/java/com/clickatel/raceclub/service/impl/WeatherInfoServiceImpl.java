package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.model.WeatherInfo;
import com.clickatel.raceclub.service.WeatherInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

    @Value("${weather.info.api.key}")
    private String WEATHER_INFO_API_KEY;

    @Override
    public WeatherInfo weatherForecast(String location) throws JsonProcessingException {
        WeatherInfo weatherInfo = new WeatherInfo();

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + WEATHER_INFO_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        String description = root.path("weather").get(0).path("description").asText();
        double temperature = root.path("main").path("temp").asDouble();
        String city = root.path("name").asText();
        double humidity = root.path("main").path("humidity").asDouble();
        double windSpeed = root.path("wind").path("speed").asDouble();

        weatherInfo.setLocation(city);
;        weatherInfo.setDescription(description);
        weatherInfo.setTemperature(temperature);
        weatherInfo.setHumidity(humidity);
        weatherInfo.setWindSpeed(windSpeed);

        return weatherInfo;
    }
}
