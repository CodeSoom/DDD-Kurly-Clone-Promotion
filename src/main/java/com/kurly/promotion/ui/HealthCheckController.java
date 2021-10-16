package com.kurly.promotion.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public Long healthCheck() {
        return System.currentTimeMillis();
    }
}
