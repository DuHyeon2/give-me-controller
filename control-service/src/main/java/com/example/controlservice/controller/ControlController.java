package com.example.controlservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/control-service")
public class ControlController {

    @GetMapping
    public String control() {
        return "Control Service is running";
    }

}
