package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {
    UUID id;
    Long createdAt;
    Long updatedAt;

    Message(UUID id, Long createdAt, Long updatedAt){
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
}
