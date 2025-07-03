package com.duhyeon.tempservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AirConditionResponseDTO {

    private Long airConditionId;

    private String name;

    private String status;

    private String wind;

    private Float temperature;

    private LocalDateTime lastUpdated;

}
