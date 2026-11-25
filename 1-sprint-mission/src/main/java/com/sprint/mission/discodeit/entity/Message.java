package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {

    UUID id;
    Long createdAt;
    Long updatedAt;

    Message(){
        createdAt = System.currentTimeMillis();
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
