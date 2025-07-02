package com.duhyeon.controlservice.service;

import com.duhyeon.controlservice.dto.AirConditionResponseDTO;

public interface ControlService {

    AirConditionResponseDTO startAirCondition(Long airConditionId);

    AirConditionResponseDTO stopAirCondition(Long airConditionId);

    AirConditionResponseDTO upTempAirCondition(Long airConditionId);

    AirConditionResponseDTO downTempAirCondition(Long airConditionId);

    AirConditionResponseDTO upWindAirCondition(Long airConditionId);

    AirConditionResponseDTO downWindAirCondition(Long airConditionId);
}
