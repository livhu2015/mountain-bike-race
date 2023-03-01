package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.RaceResult;
import com.clickatel.raceclub.repository.RaceRepository;
import com.clickatel.raceclub.service.RaceResultService;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.repository.RaceResultRepository;
import com.clickatel.raceclub.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RaceResultServiceImpl implements RaceResultService {

    @Autowired
    private RaceResultRepository raceResultRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public List<Rider> getFastestThreeRidersByRace(long id) throws RaceNotFoundException {

        Race race = raceRepository.findRaceById(id);
        RaceResult raceResult = raceResultRepository.findRaceResultByRace(race);
        if (raceResult == null) {
            throw new RaceNotFoundException("Race not found");
        }

        Set<Rider> riders = raceResult.getRiders();

        System.out.println("Initial Set: " + riders);
        Set<Rider> sortedRiders = new TreeSet<>(Comparator.comparing(Rider::getDuration));
        sortedRiders.addAll(riders);

        System.out.println("Sorted Set: " + sortedRiders);

        if (sortedRiders == null) {
            return new ArrayList<>();
        }

        return sortedRiders.stream().collect(Collectors.toList()).subList(0, Math.min(riders.size(), 3 ));
        //return sortedRiders.stream().toList().subList(0, Math.min(riders.size(), 3));
    }

    @Override
    public List<Rider> getRidersDidNotFinish(long id) {
        List<Rider> ridersDidNotFinish = new ArrayList<>();

        try {
            Race race = raceRepository.findRaceById(id);
            if (race == null) {
                throw new RaceNotFoundException("Race not found");
            }
            RaceResult raceResults = raceResultRepository.findRaceResultByRace(race);
            for (Rider rider : raceResults.getRiders()) {
                if ( rider.getDuration() < 120.0) {
                    ridersDidNotFinish.add(rider);
                }
            }

        } catch (RaceNotFoundException e) {
            throw new RuntimeException(e);
        }

        return ridersDidNotFinish;
    }

    @Override
    public List<Rider> getRidersDidNotTakePartInRace(long id) {
        List<Rider> ridersDidNotTakePart = new ArrayList<>();

        try {
            Race race = raceRepository.findRaceById(id);
            if (race == null) {
                throw new RaceNotFoundException("Race not found");
            }
            RaceResult raceResults = raceResultRepository.findRaceResultByRace(race);
            for (Rider rider : raceResults.getRiders()) {
                if (rider != null && rider.getDuration() == null) {
                    ridersDidNotTakePart.add(rider);
                }
            }

        } catch (RaceNotFoundException e) {
            throw new RuntimeException(e);
        }

        return ridersDidNotTakePart;
    }
}
