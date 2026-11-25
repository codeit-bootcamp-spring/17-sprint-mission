package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Thread {

    UUID id;
    Long createdAt;
    Long updatedAt;

    Thread(UUID id, Long createdAt, Long updatedAt){
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
}
