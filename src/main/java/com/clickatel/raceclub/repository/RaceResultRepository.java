package com.clickatel.raceclub.repository;

import com.clickatel.raceclub.model.RaceResult;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    RaceResult findRaceResultByRace(Race race);
}
