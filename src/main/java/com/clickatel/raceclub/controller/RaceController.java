package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RaceDto;
import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/race")
public class RaceController {
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceResultService) {
        this.raceService = raceResultService;
    }

    @GetMapping("/get/{raceId}")
    public Race getRaceById(@PathVariable Long raceId) throws RaceNotFoundException {

        return raceService.getRaceById(raceId);
    }
    @GetMapping("/all/races")
    public List<Race> getRaces() throws RaceNotFoundException {
        return raceService.getRaces();
    }
    @PostMapping("/create/race")
    public void createRace(RaceDto race) {
        raceService.createRace(race);
    }
    @DeleteMapping("/delete/{raceId}")
    public void deleteRace(@PathVariable Long raceId) {
        raceService.deleteRace(raceId);
    }
}
