package com.duhyeon.controlservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "air_condition")
@Getter
@Setter
public class AirCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airConditionId;

    private String name;

    private String status;

    private String wind;

    private Float temperature;

    private LocalDateTime lastUpdated;

    public void on() {
        this.status = "ON";
        this.lastUpdated = LocalDateTime.now();
    }
    public void off() {
        this.status = "OFF";
        this.lastUpdated = LocalDateTime.now();
    }
    public void increaseTemperature() {
        this.temperature += 1;
        this.lastUpdated = LocalDateTime.now();
    }
    public void decreaseTemperature() {
        this.temperature -= 1;
        this.lastUpdated = LocalDateTime.now();
    }
}
