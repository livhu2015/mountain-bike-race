package com.clickatel.raceclub.service;

import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceService {
    List<Race> getRaces();

    Race createRace(Race rider);

    Rider updateRace(long id, Race rider);

    void deleteRace(long id);

    Race getRaceById(long raceId);
}
