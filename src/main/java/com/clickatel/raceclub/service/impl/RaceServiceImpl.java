package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.repository.RaceRepository;
import com.clickatel.raceclub.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Race getRaceById(long raceId) {
        return raceRepository.findRaceById(raceId);
    }
    @Override
    public List<Race> getRaces() {
        return raceRepository.findAll();
    }

    @Override
    public Race createRace(Race rider) {
        return raceRepository.save(rider);
    }

    @Override
    public Rider updateRace(long id, Race rider) {
        return null;
    }

    @Override
    public void deleteRace(long id) {
        raceRepository.deleteById(id);
    }
}
