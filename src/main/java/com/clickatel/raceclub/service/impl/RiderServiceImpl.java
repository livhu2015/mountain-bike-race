package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.dto.RiderDto;
import com.clickatel.raceclub.exception.RiderNotFoundException;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RiderService;
import com.clickatel.raceclub.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private RiderRepository riderRepository;
    public Rider getRiderById(Long riderId) {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            return optionalRider.get();
        }
        return new Rider();
    }

    public List<Rider> getRiders() {
        List<Rider> riders = new ArrayList<>();
        riders = riderRepository.findAll();
        if (riders.isEmpty()) {
            throw new RiderNotFoundException("Riders not found!");
        }
        return riders;
    }

    public void createRider(RiderDto rider) {
        Rider createdRider = Rider.builder()
                .name(rider.getName())
                .email(rider.getEmail())
                .duration(rider.getDuration())
                .age(rider.getAge())
                .build();
        riderRepository.save(createdRider);
    }

    public Rider updateRider(Long id, Rider rider) {
        Rider updatedRider = null;
        Optional<Rider> existingRider = riderRepository.findById(id);

        if (!existingRider.isPresent()) {
            throw new RiderNotFoundException("Rider with id:  "+ id+ "not found!");
        }
        if (existingRider.isPresent()) {
            updatedRider = existingRider.get();
            if (!rider.getName().isEmpty()) updatedRider.setName(rider.getName());
            if (!rider.getEmail().isEmpty()) updatedRider.setEmail(rider.getEmail());
            if (rider.getAge() > 0) updatedRider.setAge(rider.getAge());
        }

        Rider createdRider = Rider.builder()
                .name(updatedRider.getName())
                .email(updatedRider.getEmail())
                .duration(updatedRider.getDuration())
                .age(updatedRider.getAge())
                .build();
        riderRepository.save(createdRider);

        return createdRider;
    }

    public void deleteRider(Long id) throws RiderNotFoundException {
        riderRepository.deleteById(id);
    }
}
