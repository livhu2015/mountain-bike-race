package com.clickatel.raceclub.service;

import com.clickatel.raceclub.model.Rider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RiderService {
    Rider getRiderById(Long riderId);

    List<Rider> getRiders();

    Rider createRider(Rider rider);

    Rider updateRider(Long id, Rider rider);

    void deleteRider(Long id);
}
