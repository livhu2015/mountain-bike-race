package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RaceDto;
import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.service.RaceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/race")
@Api(value = "Race Management System")
public class RaceController {
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Get a race by ID", response = Race.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the race"),
            @ApiResponse(code = 404, message = "The race ID does not exist")
    })
    public Race getRaceById(@PathVariable Long id) throws RaceNotFoundException {

        return raceService.getRaceById(id);
    }

    @GetMapping("/all/races")
    @ApiOperation(value = "Get all races", response = List.class)
    public List<Race> getRaces() throws RaceNotFoundException {
        return raceService.getRaces();
    }

    @PostMapping("/create/race")
    @ApiOperation(value = "Create a new race")
    public void createRace(@RequestBody RaceDto race) {
        raceService.createRace(race);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a race by ID")
    public void deleteRace(@PathVariable Long id) {
        raceService.deleteRace(id);
    }
}
