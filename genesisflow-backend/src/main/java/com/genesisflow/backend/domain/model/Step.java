package com.genesisflow.backend.domain.model;

import java.util.UUID;

public class Step {

    private final UUID id;
    private final String name;
    private StepStatus status;

    public Step(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.status = StepStatus.PENDING;
    }

    public void complete() {
        if (status != StepStatus.PENDING) {
            throw new IllegalStateException("Step cannot be completed");
        }
        this.status = StepStatus.COMPLETED;
    }

    public StepStatus getStatus() {
        return status;
    }
}
