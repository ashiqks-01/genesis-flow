package com.genesisflow.backend.infrastructure.web.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health check controller to verify project sanity and service availability.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    /**
     * Basic health check endpoint.
     * 
     * @return ResponseEntity with health status
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", LocalDateTime.now());
        healthStatus.put("service", "GenesisFlow Backend");
        healthStatus.put("version", "1.0.0");

        return ResponseEntity.ok(healthStatus);
    }

    /**
     * Detailed health check with system information.
     * 
     * @return ResponseEntity with detailed health status
     */
    @GetMapping("/detailed")
    public ResponseEntity<Map<String, Object>> detailedHealth() {
        Map<String, Object> healthStatus = new HashMap<>();

        // Basic info
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", LocalDateTime.now());
        healthStatus.put("service", "GenesisFlow Backend");
        healthStatus.put("version", "1.0.0");

        // System info
        Map<String, Object> systemInfo = new HashMap<>();
        Runtime runtime = Runtime.getRuntime();
        systemInfo.put("availableProcessors", runtime.availableProcessors());
        systemInfo.put("freeMemory", runtime.freeMemory());
        systemInfo.put("totalMemory", runtime.totalMemory());
        systemInfo.put("maxMemory", runtime.maxMemory());
        systemInfo.put("javaVersion", System.getProperty("java.version"));
        systemInfo.put("osName", System.getProperty("os.name"));
        systemInfo.put("osVersion", System.getProperty("os.version"));

        healthStatus.put("system", systemInfo);

        return ResponseEntity.ok(healthStatus);
    }

    /**
     * Readiness probe endpoint.
     * 
     * @return ResponseEntity indicating if the service is ready to accept traffic
     */
    @GetMapping("/ready")
    public ResponseEntity<Map<String, String>> ready() {
        Map<String, String> readiness = new HashMap<>();
        readiness.put("status", "READY");
        readiness.put("message", "Service is ready to accept requests");

        return ResponseEntity.ok(readiness);
    }

    /**
     * Liveness probe endpoint.
     * 
     * @return ResponseEntity indicating if the service is alive
     */
    @GetMapping("/live")
    public ResponseEntity<Map<String, String>> live() {
        Map<String, String> liveness = new HashMap<>();
        liveness.put("status", "ALIVE");
        liveness.put("message", "Service is running");

        return ResponseEntity.ok(liveness);
    }
}
