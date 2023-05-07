package com.imagetopdf.imagetopdf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyController {
    @GetMapping("/ping")
    public String healthyApi(){
        return "pong";
    }
}
