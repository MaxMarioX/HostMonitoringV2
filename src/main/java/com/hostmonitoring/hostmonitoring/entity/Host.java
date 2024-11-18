package com.hostmonitoring.hostmonitoring.entity;

import jakarta.persistence.*;
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

    @Column(nullable = true)
    private LocalTime lastTime;

    @Column(nullable = true)
    private LocalDate lastDate;

    public void setDefaultDate()
    {
        this.lastDate = LocalDate.now();
    }

    public void setDefaultTime()
    {
        this.lastTime = LocalTime.now();
    }
}
