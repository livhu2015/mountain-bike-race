package com.clickatel.raceclub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;
    @Column
    private LocalTime startTime;
    @Column
    private LocalTime endTime;

    @Column
    String country;
//
//    @ManyToOne
//    @JoinColumn(name = "weather_info_id")
//    private WeatherInfo weatherInfo;
}
