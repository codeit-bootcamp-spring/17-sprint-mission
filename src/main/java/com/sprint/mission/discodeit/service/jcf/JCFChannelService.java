package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;

public class JCFChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();


    //// ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String channelName, UUID serverId) {

        return null;
    }

    @Override
    public Channel findById(UUID id) {
        return null;
    }

    @Override
    public List<Channel> findAllById(UUID... ids) {
        return List.of();
    }

    @Override
    public List<Channel> findAll() {
        return List.of();
    }

    @Override
    public Channel update(UUID id, String channelName, UUID serverId) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
