package com.sprint.mission.discodeit.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Channel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /// 도메인 공통 필드
    private final UUID id = UUID.randomUUID();
    private final Long createdAt = System.currentTimeMillis();
    private Long updatedAt;

    /// 채널 이름
    private String name;

    /// 채널이 속한 서버 Id
    private UUID serverId;

    public Channel(String name, UUID serverId) {
        update(name, serverId);
    }

    /// / Setter

    public Channel update(String name, UUID serverId) {
        this.name = name;
        this.serverId = serverId;

        this.updatedAt = System.currentTimeMillis();
        return this;
    }

    /// / Getter

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public UUID getServerId() {
        return serverId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('{')
                .append(name)
                .append('(').append(id).append(')').append(',')
                .append(" createdAt=").append(createdAt).append(',')
                .append(" updatedAt=").append(updatedAt).append(',')
                .append(" serverId=").append(serverId).append(')')
                .append("}");
        return sb.toString();
    }
}
