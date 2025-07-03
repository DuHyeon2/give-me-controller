package com.duhyeon.tempservice.domain;

import com.duhyeon.tempservice.dto.AirConditionResponseDTO;
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

    public AirConditionResponseDTO toResponseDTO() {
        return AirConditionResponseDTO.builder()
                .airConditionId(this.airConditionId)
                .name(this.name)
                .status(this.status)
                .wind(this.wind)
                .temperature(this.temperature)
                .lastUpdated(this.lastUpdated)
                .build();
    }
}
