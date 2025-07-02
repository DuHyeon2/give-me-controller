package com.duhyeon.controlservice.service.impl;

import com.duhyeon.controlservice.domain.AirCondition;
import com.duhyeon.controlservice.dto.AirConditionResponseDTO;
import com.duhyeon.controlservice.repository.AirConditionRepository;
import com.duhyeon.controlservice.service.ControlService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ControlServiceImpl implements ControlService {

    private final AirConditionRepository airConditionRepository;

    public ControlServiceImpl(AirConditionRepository airConditionRepository) {
        this.airConditionRepository = airConditionRepository;
    }



    @Override
    public AirConditionResponseDTO startAirCondition(Long airConditionId) {
        try{
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.on();
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Air condition started successfully");
        }catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to start air condition: " + e.getMessage());
        }
    }

    @Override
    public AirConditionResponseDTO stopAirCondition(Long airConditionId) {
        try {
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.off();
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Air condition stopped successfully");
        } catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to stop air condition: " + e.getMessage());
        }
    }

    @Override
    public AirConditionResponseDTO upTempAirCondition(Long airConditionId) {
        try {
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.increaseTemperature();
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Temperature increased successfully");
        } catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to increase temperature: " + e.getMessage());
        }
    }

    @Override
    public AirConditionResponseDTO downTempAirCondition(Long airConditionId) {
        try {
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.decreaseTemperature();
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Temperature decreased successfully");
        } catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to decrease temperature: " + e.getMessage());
        }
    }

    @Override
    public AirConditionResponseDTO upWindAirCondition(Long airConditionId) {
        try {
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.setWind(nextWind(airCondition.getWind()));
            airCondition.setLastUpdated(LocalDateTime.now());
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Wind direction set to UP successfully");
        } catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to set wind direction: " + e.getMessage());
        }
    }

    @Override
    public AirConditionResponseDTO downWindAirCondition(Long airConditionId) {
        try {
            AirCondition airCondition = getAirCondition(airConditionId);

            airCondition.setWind(previousWind(airCondition.getWind()));
            airCondition.setLastUpdated(LocalDateTime.now());
            airConditionRepository.save(airCondition);
            return new AirConditionResponseDTO(true, "Wind direction set to DOWN successfully");
        } catch (Exception e) {
            return new AirConditionResponseDTO(false, "Failed to set wind direction: " + e.getMessage());
        }
    }

    private AirCondition getAirCondition(Long airConditionId) {
        return airConditionRepository.findById(airConditionId)
                .orElseThrow(() -> new IllegalArgumentException("AirCondition not found with id: " + airConditionId));
    }

    private String nextWind(String currentWind) {
        return switch (currentWind.toUpperCase()) {
            case "LOW" -> "MEDIUM";
            case "MEDIUM" -> "HIGH";
            default -> "HIGH";
        };
    }
    private String previousWind(String currentWind) {
        return switch (currentWind.toUpperCase()) {
            case "HIGH" -> "MEDIUM";
            case "MEDIUM" -> "LOW";
            default -> "LOW";
        };
    }
}
