package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID id;
    public UUID getId(){
        return id;
    }

    private final Long createdAt;
    public Long getCreatedAt(){
        return createdAt;
    }

    private Long updatedAt;
    public Long getUpdatedAt(){
        return updatedAt;
    }
    public void updateTimestamp(){
        updatedAt = System.currentTimeMillis();
    }

    public Entity(){
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = createdAt;
    }

}
