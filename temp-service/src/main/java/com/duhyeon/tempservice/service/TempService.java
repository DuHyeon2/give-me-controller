package com.duhyeon.tempservice.service;

import com.duhyeon.tempservice.dto.AirConditionResponseDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface TempService {

    AirConditionResponseDTO getAirCondition();

    SseEmitter getAirConditionSseEmitter();

    void updateAirCondition(AirConditionResponseDTO airConditionResponseDTO);
}
