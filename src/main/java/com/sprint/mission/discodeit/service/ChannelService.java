package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    /// Create
    Channel create(String channelName, UUID serverId);

    /// Read
    Channel findById(UUID id);

    List<Channel> findAllById(UUID... ids);

    List<Channel> findAll();

    /// Update
    Channel update(UUID id, String channelName, UUID serverId);

    /// Delete
    void delete(UUID id);
}
