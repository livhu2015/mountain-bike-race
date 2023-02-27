package com.clickatel.raceclub.service;

import com.clickatel.raceclub.model.WeatherInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface WeatherInfoService {
    WeatherInfo weatherForecast(String city) throws JsonProcessingException;
}
