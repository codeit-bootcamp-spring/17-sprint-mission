package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {
    private final ChannelRepository repository;

    public BasicChannelService(ChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Channel create(String name, UUID serverId) {
        return repository.save( new Channel(name, serverId));
    }

    @Override
    public Channel findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<Channel> findAll() {
        return repository.findAll();
    }

    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        Channel entity = repository.findById(id).update(name,serverId);
        repository.delete(id);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
