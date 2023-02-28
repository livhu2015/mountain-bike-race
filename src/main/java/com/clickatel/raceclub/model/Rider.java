package com.clickatel.raceclub.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rider")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull(message = "Rider's name may not be null")
    private String name;
    @Column
    private String email;
    @Column
    private int age;
    @Column
    private Double duration;

}