package com.clickatel.raceclub.service.impl;

import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.service.RiderService;
import com.clickatel.raceclub.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return riderRepository.findAll();
    }

    public Rider createRider(Rider rider) {
        return riderRepository.save(rider);
    }

    public Rider updateRider(Long id, Rider rider) {
        Rider updatedRider = null;
        Optional<Rider> existingRider = riderRepository.findById(id);
        if (existingRider.isPresent()) {
            updatedRider = existingRider.get();
            if (!rider.getName().isEmpty()) updatedRider.setName(rider.getName());
            if (!rider.getEmail().isEmpty()) updatedRider.setEmail(rider.getEmail());
            if (rider.getAge() > 0) updatedRider.setAge(rider.getAge());
        }

        return createRider(updatedRider);
    }

    public void deleteRider(Long id) {
        riderRepository.deleteById(id);
    }
}
