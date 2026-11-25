package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    /// Create
    Channel create(String name, UUID serverId);

    /// Read
    Channel findById(UUID id);

    List<Channel> findAllById(List<UUID> ids);

    List<Channel> findAll();

    /// Update
    Channel update(UUID id, String name, UUID serverId);

    /// Delete
    void delete(UUID id);
}
