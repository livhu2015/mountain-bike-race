package com.clickatel.raceclub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherInfo {
    private String location;
    private String description;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
}
