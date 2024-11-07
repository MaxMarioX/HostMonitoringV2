package com.hostmonitoring.hostmonitoring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "hosts_availability")
@Setter
@Getter
public class HostAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true)
    private LocalTime lastTime;

    @Column(nullable = true)
    private LocalDate lastDate;

    @OneToOne(mappedBy = "hosts")
    private Host host;
}
