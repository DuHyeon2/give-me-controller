package com.duhyeon.alarmservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @RequestMapping("/health")
    public String getServiceInfo() {
        return "Alarm Service is running";
    }
}
