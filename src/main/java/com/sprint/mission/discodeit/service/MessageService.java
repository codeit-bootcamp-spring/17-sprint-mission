package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    /// Create
    Message create(String content, UUID authorId, UUID channelId);

    /// Read
    Message findById(UUID id);

    List<Message> findAllById(UUID... ids);

    List<Message> findAll();

    /// Update
    Message update(UUID id, String content, UUID authorId, UUID channelId);

    /// Delete
    void delete(UUID id);
}
