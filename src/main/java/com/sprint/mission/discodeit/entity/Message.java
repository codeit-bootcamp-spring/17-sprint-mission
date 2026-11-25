package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {
    /// 도메인 공통 필드
    private final UUID id = UUID.randomUUID();
    private final Long createdAt = System.currentTimeMillis();
    private Long updatedAt;

    /// 메세지 본문, 마크다운
    private String content;
    /// 작성한 유저의 UUID
    private UUID authorId;
    /// 작성된 채널의 UUID
    private UUID channelId;

    public Message(String content, UUID authorId, UUID channelId) {
        update(content, authorId, channelId);
    }

    //// Setter

    public Message update(String content, UUID authorId, UUID channelId) {
        this.content = content;
        this.authorId = authorId;
        this.channelId = channelId;

        this.updatedAt = System.currentTimeMillis();
        return this;
    }

    //// Getter

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getContent() {
        return content;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public UUID getChannelId() {
        return channelId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('{')
                .append('(').append(id).append(')').append(',')
                .append(" createdAt=").append(createdAt).append(',')
                .append(" updatedAt=").append(updatedAt).append(',')
                .append(" content=").append(content).append(',')
                .append(" authorId=").append(authorId).append(')').append(',')
                .append(" channelId=").append(channelId).append(')')
                .append("}");
        return sb.toString();
    }
}
