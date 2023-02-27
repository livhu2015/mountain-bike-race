package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RaceController {
    @Qualifier("RaceService")
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceResultService) {
        this.raceService = raceResultService;
    }
    @GetMapping("/race/{raceId}")
    public Race getRaceById(@PathVariable Long raceId) {
        return raceService.getRaceById(raceId);
    }
    @GetMapping("/all/races")
    public List<Race> getRaces() {
        return raceService.getRaces();
    }
    @PostMapping("/create/race}")
    public Race createRace(Race race) {
        return raceService.createRace(race);
    }

    @PutMapping("/update/race/{raceId}")
    public Rider updateRace(@PathVariable Long raceId, @RequestBody Race race) {

        return raceService.updateRace(raceId, race);
    }

    @DeleteMapping("/delete/race/{raceId}")
    public void deleteRace(@PathVariable Long raceId) {
        raceService.deleteRace(raceId);
    }
}
