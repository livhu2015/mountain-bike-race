package com.clickatel.raceclub.service;

import com.clickatel.raceclub.exception.RaceNotFoundException;
import com.clickatel.raceclub.model.Race;
import com.clickatel.raceclub.model.RaceResult;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.repository.RaceRepository;
import com.clickatel.raceclub.repository.RaceResultRepository;
import com.clickatel.raceclub.service.impl.RaceResultServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RaceResultServiceImplTest {
    @Mock
    private RaceResultRepository raceResultRepository;
    @Mock
    private RaceRepository raceRepository;

    @Test
    void contextLoads() {
    }

    @InjectMocks
    private RaceResultServiceImpl raceResultService;

    @Test
    public void testGetFastestThreeRidersByRace() throws RaceNotFoundException {

        Race race = new Race(1L, "Tour de France", "08h00", "16h00", "France");
        Set<Rider> riders = new HashSet<>();
        Rider rider1 = new Rider(1L, "John Doe", "john.doe@example.com",  30, 810.0);
        Rider rider2 = new Rider(2L, "Jane Doe", "john.doe@example.com",  30, 170.0);
        Rider rider3 = new Rider(3L, "Alex Smith", "john.doe@example.com",  30, 60.0);
        Rider rider4 = new Rider(4L, "Shoe Smith", "john.doe@example.com",  30, 120.0);
        Rider rider5 = new Rider(5L, "Mic Smith", "john.doe@example.com",  30, 110.0);

        riders.add(rider1);
        riders.add(rider2);
        riders.add(rider3);
        riders.add(rider4);
        riders.add(rider5);
        RaceResult raceResult = new RaceResult(1L, race, riders);;

        when(raceRepository.findRaceById(race.getId())).thenReturn(race);
        when(raceResultRepository.findRaceResultByRace(race)).thenReturn(raceResult);

        List<Rider> fastestThreeRiders = raceResultService.getFastestThreeRidersByRace(race.getId());

        assertNotNull(fastestThreeRiders);
        assertEquals(3, fastestThreeRiders.size());
        assertEquals(rider3.getId(), fastestThreeRiders.get(0).getId());
        assertEquals(rider5.getId(), fastestThreeRiders.get(1).getId());
        assertEquals(rider4.getId(), fastestThreeRiders.get(2).getId());
        verify(raceRepository, times(1)).findRaceById(race.getId());
        verify(raceResultRepository, times(1)).findRaceResultByRace(race);
    }

    @Test
    public void testGetRidersDidNotFinish() {

        Race race = new Race(1L, "Tour de France", "08h00", "16h00", "France");
        Rider rider1= new Rider(1L, "John Doe", "john.doe@example.com",  30, 125.0);
        Rider rider2 = new Rider(2L, "Mick Doe", "john.doe@example.com",  30, 100.0);
        Rider rider3 = new Rider(3L, "alex Doe", "john.doe@example.com",  30, 80.5);

        Set<Rider> riders = new HashSet<>();
        riders.add(rider1);
        riders.add(rider2);
        riders.add(rider3);
        RaceResult raceResult = new RaceResult(1L, race, riders);

        when(raceResultRepository.findRaceResultByRace(race)).thenReturn(raceResult);
        when(raceRepository.findRaceById(race.getId())).thenReturn(race);

        List<Rider> ridersDidNotFinish = raceResultService.getRidersDidNotFinish(race.getId());
        assertNotNull(ridersDidNotFinish);
        assertEquals(2, ridersDidNotFinish.size());
        verify(raceRepository, times(1)).findRaceById(race.getId());
        verify(raceResultRepository, times(1)).findRaceResultByRace(race);
    }

    @Test
    public void testGetRidersDidNotTakePartInRace() {

        Race race = new Race(1L, "Tour de France", "08h00", "16h00", "France");
        Rider rider1= new Rider(1L, "John Doe", "john.doe@example.com",  30, 120.0);
        Rider rider2 = new Rider(2L, "Jane Doe", "john.doe@example.com",  28, null);
        Rider rider3 = new Rider(3L, "Alex Doe", "john.doe@example.com",  23,400.5);
        Rider rider4 = new Rider(4L, "Mick Doe", "john.doe@example.com",  19, null);

        Set<Rider> riders = new HashSet<>();
        riders.add(rider1);
        riders.add(rider2);

        RaceResult raceResult = new RaceResult(1L, race, riders);;

        when(raceResultRepository.findRaceResultByRace(race)).thenReturn(raceResult);
        when(raceRepository.findRaceById(race.getId())).thenReturn(race);

        List<Rider> ridersDidNotFinish = raceResultService.getRidersDidNotTakePartInRace(race.getId());

        assertNotNull(ridersDidNotFinish);
        assertEquals(1, ridersDidNotFinish.size());
        assertEquals(rider2.getId(), ridersDidNotFinish.get(0).getId());
        verify(raceRepository, times(1)).findRaceById(race.getId());
        verify(raceResultRepository, times(1)).findRaceResultByRace(race);
    }

}