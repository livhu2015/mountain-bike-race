package com.clickatel.raceclub.service;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Rider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceResultService {
    List<Rider> getFastestThreeRidersByRace(long id) throws RaceNotFoundException;

    List<Rider> getRidersDidNotFinish(long id);

    List<Rider> getRidersDidNotTakePartInRace(long id);
}
