package com.clickatel.raceclub.service;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Rider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceResultService {
    List<Rider> getFastestThreeRidersByRace(long raceId) throws RaceNotFoundException;

    List<Rider> getRidersDidNotFinish(Long raceId);

    List<Rider> getRidersDidNotTakePartInRace(Long raceId);
}
