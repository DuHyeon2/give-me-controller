package com.duhyeon.tempservice.controller;

import com.duhyeon.tempservice.service.TempService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class TempSseController {

    private final TempService tempService;

    public TempSseController(TempService tempService) {
        this.tempService = tempService;
    }

    @GetMapping(value = "air-condition", produces = "text/event-stream")
    public SseEmitter streamAirCondition() {
        return tempService.getAirConditionSseEmitter();
    }
}
