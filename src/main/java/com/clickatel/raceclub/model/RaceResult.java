package com.clickatel.raceclub.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="race_riders")
    private Set<Rider> riders;



}
