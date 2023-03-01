package com.clickatel.raceclub.service;
import com.clickatel.raceclub.dto.RiderDto;
import com.clickatel.raceclub.model.Rider;
import com.clickatel.raceclub.repository.RiderRepository;
import com.clickatel.raceclub.service.impl.RaceResultServiceImpl;
import com.clickatel.raceclub.service.impl.RiderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RiderServiceImplTest {

    @Mock
    private RiderRepository riderRepository;

    @InjectMocks
    private RiderServiceImpl riderService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateRider() {
        RiderDto rider = RiderDto.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .age(30)
                .duration(80L)
                .build();
        when(riderRepository.save(any(Rider.class))).thenReturn(any());
       riderService.createRider(rider);
        verify(riderRepository, times(1)).save(any());
    }

    @Test
    public void testGetRider() {
        Rider rider = new Rider(1L, "John Doe", "john.doe@example.com",  30, 80.0);
        when(riderRepository.findById(anyLong())).thenReturn(Optional.of(rider));
        Rider retrievedRider = riderService.getRiderById(1L);
        assertNotNull(retrievedRider);
        assertEquals(rider.getId(), retrievedRider.getId());
        assertEquals(rider.getName(), retrievedRider.getName());
        assertEquals(rider.getEmail(), retrievedRider.getEmail());
        assertEquals(rider.getAge(), retrievedRider.getAge());
        verify(riderRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateRider() {
        Rider rider = new Rider(1L, "John Doe", "john.doe@example.com",  30, 80.0);
        Rider updatedRider = new Rider(1L, "Jane Doe", "jane.doe@example.com",  45, 80.0);
        when(riderRepository.findById(anyLong())).thenReturn(Optional.of(rider));
        when(riderRepository.save(any(Rider.class))).thenReturn(updatedRider);

        Rider result = riderService.updateRider(1L, updatedRider);
        assertNotNull(result);
        assertEquals(updatedRider.getName(), result.getName());
        assertEquals(updatedRider.getEmail(), result.getEmail());
        assertEquals(updatedRider.getAge(), result.getAge());
        verify(riderRepository, times(1)).findById(1L);
    }

}
