package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RiderDto;
import com.clickatel.raceclub.service.RiderService;
import com.clickatel.raceclub.model.Rider;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rider")
@Api(value = "Rider Management System")
public class RiderController {
    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @ApiOperation(value = "Get a rider by ID")
    @GetMapping("/riders/{id}")
    public Rider getRiderById(@PathVariable(value = "id") long riderId) {
        Rider rider = riderService.getRiderById(riderId);
        return rider;
    }

    @ApiOperation(value = "Get all riders")
    @GetMapping("/all/riders")
    public List<Rider> getRiders() {
        List<Rider> riders = riderService.getRiders();
        return riders;
    }

    @ApiOperation(value = "Create a new rider")
    @PostMapping("/create/rider")
    public void createRider(@RequestBody RiderDto rider) {
        riderService.createRider(rider);
    }

    @ApiOperation(value = "Update an existing rider")
    @PutMapping("/update/{id}")
    public Rider updateRider(@PathVariable Long id, @RequestBody Rider rider) {
        Rider updatedRider = riderService.updateRider(id, rider);
        return updatedRider;
    }

    @ApiOperation(value = "Delete a rider by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteRider(@PathVariable Long id) {
        riderService.deleteRider(id);
    }
}
