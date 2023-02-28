package com.clickatel.raceclub.service;

import com.clickatel.raceclub.dto.RaceDto;
import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceService {
    List<Race> getRaces() throws RaceNotFoundException;

    void createRace(RaceDto rider);

    void deleteRace(long id);

    Race getRaceById(long id) throws RaceNotFoundException;
}
