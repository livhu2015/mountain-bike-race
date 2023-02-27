package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RaceResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RaceResultController {
    @Qualifier("RaceResultService")
    private final RaceResultService raceResultService;

    @Autowired
    public RaceResultController(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @GetMapping("/fastest-three-riders")
    public List<Rider> getFastestThreeRidersByRace(@PathVariable long raceId) throws RaceNotFoundException {
        return raceResultService.getFastestThreeRidersByRace(raceId);
    }

    @GetMapping("/riders-did-not-finish")
    public List<Rider> getRidersDidNotFinish(@PathVariable Long raceId) {
        return raceResultService.getRidersDidNotFinish(raceId);
    }

    @GetMapping("/riders-did-not-take-part")
    public List<Rider> getRidersDidNotTakePartInRace(@PathVariable Long raceId) {
        return raceResultService.getRidersDidNotTakePartInRace(raceId);
    }

}
