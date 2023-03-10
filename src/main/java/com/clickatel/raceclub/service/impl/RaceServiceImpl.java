package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.dto.RaceDto;
import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.repository.RaceRepository;
import com.clickatel.raceclub.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RaceServiceImpl implements RaceService {


    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Race getRaceById(long id) throws RaceNotFoundException {
        try {
            Race race = raceRepository.findRaceById(id);
            if (race == null) {
                throw new RaceNotFoundException("No Race found" + RaceDto.builder().build());
            }
            return race;

        } catch (Exception ex ) {
            throw new RaceNotFoundException("Not Found");
        }

    }
    @Override
    public List<Race> getRaces() throws RaceNotFoundException {
        List<Race> races = new ArrayList<>();
        try {
            races = raceRepository.findAll();

            if (races == null) {
                throw new RaceNotFoundException("No Races found");
            }
        } catch (Exception ex) {
            return new ArrayList<>();
        }

        return races;
    }

    @Override
    public void createRace(RaceDto race) {

        Race createdRace = Race.builder()
                .title(race.getTitle())
                .country(race.getCountry())
                .endTime(race.getEndTime())
                .startTime(race.getStartTime())
                .build();

        raceRepository.save(createdRace);
    }
    @Override
    public void deleteRace(long id) {
        raceRepository.deleteById(id);
    }

}
