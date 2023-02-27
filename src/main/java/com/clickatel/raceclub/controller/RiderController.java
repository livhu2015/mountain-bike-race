package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.service.RiderService;
import com.clickatel.raceclub.model.Rider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RiderController {
    @Qualifier("RiderService")
    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/riders/{id}")
    public Rider getRiderById(@PathVariable(value = "id") Long riderId) {
        // code to retrieve the rider with the given ID
        Rider rider = riderService.getRiderById(riderId);
        return rider;
    }

    @GetMapping("/all/riders")
    public List<Rider> getRiders() {
        //retrieve a list of riders
        List<Rider> riders = riderService.getRiders();
        return riders;
    }

    @PostMapping("/create/riders")
    public Rider createRider(@RequestBody Rider rider) {
        // create a new rider with the data from the request body
        Rider newRider = riderService.createRider(rider);
        return newRider;
    }

    @PutMapping("/update/riders/{id}")
    public Rider updateRider(@PathVariable Long id, @RequestBody Rider rider) {
        // code to update the rider with the given ID using the data from the request body
        Rider updatedRider = riderService.updateRider(id, rider);
        return updatedRider;
    }

    @DeleteMapping("/delete/riders/{id}")
    public void deleteRider(@PathVariable Long id) {
        // code to delete the rider with the given ID
        riderService.deleteRider(id);
    }
}
