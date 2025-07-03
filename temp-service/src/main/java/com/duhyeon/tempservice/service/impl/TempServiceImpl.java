package com.duhyeon.tempservice.service.impl;

import com.duhyeon.tempservice.dto.AirConditionResponseDTO;
import com.duhyeon.tempservice.repository.AirConditionRepository;
import com.duhyeon.tempservice.service.TempService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TempServiceImpl implements TempService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private AirConditionResponseDTO currentAirCondition = new AirConditionResponseDTO();

    private final AirConditionRepository airConditionRepository;

    public TempServiceImpl(AirConditionRepository airConditionRepository) {
        this.airConditionRepository = airConditionRepository;
    }

    @Override
    public AirConditionResponseDTO getAirCondition() {
        try {
            AirConditionResponseDTO airCondition = airConditionRepository.findAll().stream().findFirst().get().toResponseDTO();
            this.currentAirCondition = airCondition;
            return airCondition;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching air conditions: " + e.getMessage(), e);
        }
    }

    public SseEmitter getAirConditionSseEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // 타임아웃 길게
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        try {
            emitter.send(SseEmitter.event().name("init").data("Air condition service started"));
        } catch (Exception ignore) {}

        return emitter;
    }

    @Override
    public void updateAirCondition(AirConditionResponseDTO airConditionResponseDTO) {
        this.currentAirCondition = airConditionResponseDTO;
        broadcastStatus();
    }

    private void broadcastStatus() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("status").data(currentAirCondition));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }



}
