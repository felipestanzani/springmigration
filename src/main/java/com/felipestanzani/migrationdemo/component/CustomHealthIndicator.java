package com.felipestanzani.migrationdemo.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        var serviceUp = checkService();

        if (serviceUp) {
            return Health.up().withDetail("status", "Alive and kicking!!!").build();
        }
        return Health.down().withDetail("error", "I'm feeling bad...").build();
    }

    private boolean checkService() {
        return Math.random() > 0.2;
    }
}
