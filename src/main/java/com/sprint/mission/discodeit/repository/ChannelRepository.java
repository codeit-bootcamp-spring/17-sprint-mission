package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelRepository {
    static final String DIRECTORY_NAME = "channels";
    static final String PRE_FIX = "channel_";
    static final String POST_FIX = ".ser";

    Channel save(Channel entity);

    Channel findById(UUID id);

    List<Channel> findAllById(List<UUID> ids);

    List<Channel> findAll();

    void delete(UUID id);
}
