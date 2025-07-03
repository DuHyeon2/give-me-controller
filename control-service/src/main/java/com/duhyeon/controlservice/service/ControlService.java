package com.duhyeon.controlservice.service;

import com.duhyeon.controlservice.dto.AirConditionResponseDTO;

public interface ControlService {

    AirConditionResponseDTO startAirCondition(Long airConditionId, String nickname);

    AirConditionResponseDTO stopAirCondition(Long airConditionId, String nickname);

    AirConditionResponseDTO upTempAirCondition(Long airConditionId, String nickname);

    AirConditionResponseDTO downTempAirCondition(Long airConditionId, String nickname);

    AirConditionResponseDTO upWindAirCondition(Long airConditionId, String nickname);

    AirConditionResponseDTO downWindAirCondition(Long airConditionId, String nickname);
}
