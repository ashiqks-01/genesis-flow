package com.genesisflow.backend.domain.model;

import java.util.List;
import java.util.UUID;

public class Workflow {

    private final UUID id;
    private String name;
    private WorkflowStatus status;
    private List<Step> steps;

    public Workflow(UUID id, String name, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.steps = steps;
        this.status = WorkflowStatus.CREATED;
    }

    public void start() {
        if (status != WorkflowStatus.CREATED) {
            throw new IllegalStateException("Workflow cannot be started");
        }
        this.status = WorkflowStatus.IN_PROGRESS;
    }

    public UUID getId() {
        return id;
    }

    public WorkflowStatus getStatus() {
        return status;
    }
}
