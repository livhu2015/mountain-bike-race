package com.clickatel.raceclub.service;

import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;

import java.util.List;

public interface RaceService {
    List<Race> getRaces();

    Race createRace(Race rider);

    Rider updateRace(long id, Race rider);

    void deleteRace(long id);

    Race getRaceById(long raceId);
}
