package com.clickatel.raceclub.controller;

import com.clickatel.raceclub.dto.RiderDto;
import com.clickatel.raceclub.service.RiderService;
import com.clickatel.raceclub.model.Rider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rider")
public class RiderController {
    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/riders/{id}")
    public Rider getRiderById(@PathVariable(value = "id") long riderId) {
        Rider rider = riderService.getRiderById(riderId);
        return rider;
    }

    @GetMapping("/all/riders")
    public List<Rider> getRiders() {
        List<Rider> riders = riderService.getRiders();
        return riders;
    }

    @PostMapping("/create/rider")
    public void createRider(@RequestBody RiderDto rider) {
        riderService.createRider(rider);
    }

    @PutMapping("/update/{id}")
    public Rider updateRider(@PathVariable Long id, @RequestBody Rider rider) {
        Rider updatedRider = riderService.updateRider(id, rider);
        return updatedRider;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRider(@PathVariable Long id) {
        riderService.deleteRider(id);
    }
}
