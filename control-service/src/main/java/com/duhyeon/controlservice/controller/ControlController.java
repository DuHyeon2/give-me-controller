package com.duhyeon.controlservice.controller;

import com.duhyeon.controlservice.dto.AirConditionResponseDTO;
import com.duhyeon.controlservice.service.ControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control")
public class ControlController {

    private final ControlService controlService;

    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    @GetMapping("/health")
    public String getServiceInfo() {
        return "Control Service is running";
    }

    @PutMapping("/{id}/status/on")
    public ResponseEntity<AirConditionResponseDTO> startAirCondition(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.startAirCondition(id, nickname));
    }

    @PutMapping("/{id}/status/off")
    public ResponseEntity<AirConditionResponseDTO> stopAirCondition(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.stopAirCondition(id, nickname));
    }

    @PutMapping("/{id}/temperature/up")
    public ResponseEntity<AirConditionResponseDTO> upTemperature(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.upTempAirCondition(id, nickname));
    }

    @PutMapping("/{id}/temperature/down")
    public ResponseEntity<AirConditionResponseDTO> downTemperature(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.downTempAirCondition(id, nickname));
    }

    @PutMapping("/{id}/wind/up")
    public ResponseEntity<AirConditionResponseDTO> upWind(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.upWindAirCondition(id, nickname));
    }

    @PutMapping("/{id}/wind/down")
    public ResponseEntity<AirConditionResponseDTO> downWind(@PathVariable("id") Long id, @RequestBody String nickname) {
        return ResponseEntity.ok(controlService.downWindAirCondition(id, nickname));
    }

}
