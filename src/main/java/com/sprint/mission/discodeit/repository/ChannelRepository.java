package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelRepository {
    Channel save(Channel channel);

    List<Channel> findAll();

    Channel findById(UUID id);

    List<Channel> findByName(String name);

    void deleteById(UUID id);
}
