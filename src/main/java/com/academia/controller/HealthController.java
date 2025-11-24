package com.academia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new java.util.HashMap<String, String>() {{
            put("status", "UP");
            put("message", "Sistema de Gestión Académica - API Java");
            put("timestamp", java.time.LocalDateTime.now().toString());
        }});
    }
}
