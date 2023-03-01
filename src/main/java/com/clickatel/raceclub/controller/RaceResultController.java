package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RaceResultService;
import io.swagger.annotations.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/race/results")
@Api(value = "Race Result Management System")
public class RaceResultController {

    private final RaceResultService raceResultService;

    @Autowired
    public RaceResultController(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @GetMapping("/{id}/fastest-three-riders")
    @ApiOperation(value = "Get the fastest three riders by race ID", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the fastest three riders"),
            @ApiResponse(code = 404, message = "The race ID does not exist")
    })
    public List<Rider> getFastestThreeRidersByRace(@PathVariable Long id) throws RaceNotFoundException {
        return raceResultService.getFastestThreeRidersByRace(id);
    }

    @GetMapping("/{id}/riders-did-not-finish")
    @ApiOperation(value = "Get the list of riders who did not finish the race by race ID", response = List.class)
    public List<Rider> getRidersDidNotFinish(@PathVariable Long id) {
        return raceResultService.getRidersDidNotFinish(id);
    }

    @GetMapping("/{id}/riders-did-not-take-part")
    @ApiOperation(value = "Get the list of riders who did not take part in the race by race ID", response = List.class)
    public List<Rider> getRidersDidNotTakePartInRace(@PathVariable Long id) {
        return raceResultService.getRidersDidNotTakePartInRace(id);
    }
}
