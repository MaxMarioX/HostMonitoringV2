package com.hostmonitoring.hostmonitoring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "hosts")
@Setter
@Getter
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 27)
    private String ipAddress;

    @OneToOne(mappedBy = "hosts_availability")
    private HostAvailability hostAvailability;

}
