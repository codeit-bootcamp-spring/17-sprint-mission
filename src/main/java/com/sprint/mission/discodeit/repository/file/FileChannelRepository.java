package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.List;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    @Override
    public Channel save(Channel entity) {
        return null;
    }

    @Override
    public Channel findById(UUID id) {
        return null;
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return List.of();
    }

    @Override
    public List<Channel> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
