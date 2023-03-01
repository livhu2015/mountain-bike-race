package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.WeatherInfo;
import com.clickatel.raceclub.service.RaceService;
import com.clickatel.raceclub.service.WeatherInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Api(value = "Weather Management System")
public class WeatherInfoController {
    private final WeatherInfoService weatherInfoService;

    @Autowired
    public WeatherInfoController(WeatherInfoService weatherInfoService) {
        this.weatherInfoService = weatherInfoService;
    }

    @GetMapping("/{location}/forecast")
    @ApiOperation(value = "Get a weather information by location", response = Race.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the weather information"),
            @ApiResponse(code = 404, message = "The location does not exist")
    })
    public WeatherInfo weatherForecast(@PathVariable String location) throws RaceNotFoundException, JsonProcessingException {

        return weatherInfoService.weatherForecast(location);
    }
}
