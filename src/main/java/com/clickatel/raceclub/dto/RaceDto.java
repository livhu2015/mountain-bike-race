package com.clickatel.raceclub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RaceDto {
    private String title;
    private String startTime;
    private String endTime;
    String country;
}
