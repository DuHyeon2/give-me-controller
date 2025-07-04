package com.duhyeon.tempservice.controller;

import com.duhyeon.tempservice.dto.AirConditionResponseDTO;
import com.duhyeon.tempservice.service.TempService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/temp")
public class TempController {

    private final TempService tempService;

    public TempController(TempService tempService) {
        this.tempService = tempService;
    }

    @GetMapping("/air-condition")
    public ResponseEntity<AirConditionResponseDTO> getAirCondition() {

        return ResponseEntity.ok(tempService.getAirCondition());
    }

}
