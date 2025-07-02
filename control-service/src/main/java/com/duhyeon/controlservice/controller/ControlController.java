package com.duhyeon.controlservice.controller;

import com.duhyeon.controlservice.dto.AirConditionResponseDTO;
import com.duhyeon.controlservice.service.ControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control-service")
public class ControlController {

    private final ControlService controlService;

    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    @PatchMapping("/{id}/status/on")
    public ResponseEntity<AirConditionResponseDTO> startAirCondition(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.startAirCondition(id));
    }

    @PatchMapping("/{id}/status/off")
    public ResponseEntity<AirConditionResponseDTO> stopAirCondition(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.stopAirCondition(id));
    }

    @PatchMapping("/{id}/temperature/up")
    public ResponseEntity<AirConditionResponseDTO> upTemperature(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.upTempAirCondition(id));
    }

    @PatchMapping("/{id}/temperature/down")
    public ResponseEntity<AirConditionResponseDTO> downTemperature(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.downTempAirCondition(id));
    }

    @PatchMapping("/{id}/wind/up")
    public ResponseEntity<AirConditionResponseDTO> upWind(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.upWindAirCondition(id));
    }

    @PatchMapping("/{id}/wind/down")
    public ResponseEntity<AirConditionResponseDTO> downWind(@PathVariable("id") Long id) {
        return ResponseEntity.ok(controlService.downWindAirCondition(id));
    }

}
