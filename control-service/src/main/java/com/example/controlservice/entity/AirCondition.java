package com.example.controlservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "air_condition")
public class AirCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airConditionId;

    private String name;

    private String status;

    private String mode;

    private Float temperature;

    private LocalDateTime lastUpdated;
}
