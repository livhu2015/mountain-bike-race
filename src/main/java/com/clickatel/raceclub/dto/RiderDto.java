package com.clickatel.raceclub.dto;

import com.clickatel.raceclub.util.DurationDeserializer;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class RiderDto {
    private String name;
    private String email;
    private int age;
    private double duration;
}
