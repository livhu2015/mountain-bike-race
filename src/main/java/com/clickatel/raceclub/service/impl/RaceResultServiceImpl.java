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
import java.util.stream.Stream;

@Slf4j
@Service
public class RaceResultServiceImpl implements RaceResultService {

    @Autowired
    private RaceResultRepository raceResultRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public List<Rider> getFastestThreeRidersByRace(long raceId) throws RaceNotFoundException {
//        RaceResult sortedRidersResults = raceResultRepository.findRiderOrderByDurationAsc(race);

        Race race = raceRepository.findRaceById(raceId);
        RaceResult raceResult = raceResultRepository.findRaceResultByRace(race);
        if (raceResult == null) {
            throw new RaceNotFoundException("Race not found");
        }


        Set<Rider> riders = raceResult.getRiders();

        System.out.println("Initial Set: " + riders);
        Set<Rider> sortedRiders = new TreeSet<>((r1, r2) -> r1.getDuration().compareTo(r2.getDuration()));
        sortedRiders.addAll(riders);

        System.out.println("Sorted Set: " + sortedRiders);

        if (sortedRiders == null) {
            return new ArrayList<>();
        }

        return sortedRiders.stream().toList().subList(0, Math.min(riders.size(), 3));
    }

    @Override
    public List<Rider> getRidersDidNotFinish(Long raceId) {
        List<Rider> ridersDidNotFinish = new ArrayList<>();

        try {
            Race race = raceRepository.findRaceById(raceId);
            if (race == null) {
                throw new RaceNotFoundException("Race not found");
            }
            RaceResult raceResults = raceResultRepository.findRaceResultByRace(race);
            for (Rider rider : raceResults.getRiders()) {
                if ( rider.getDuration().getSeconds() < 120) {
                    ridersDidNotFinish.add(rider);
                }
            }

        } catch (RaceNotFoundException e) {
            throw new RuntimeException(e);
        }

        return ridersDidNotFinish;
    }

    @Override
    public List<Rider> getRidersDidNotTakePartInRace(Long raceId) {
        List<Rider> ridersDidNotTakePart = new ArrayList<>();

        try {
            Race race = raceRepository.findRaceById(raceId);
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
