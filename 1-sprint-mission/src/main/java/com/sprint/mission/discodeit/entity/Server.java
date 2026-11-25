package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Server {

    UUID id;
    Long createdAt;
    Long updatedAt;

    Server(){
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
