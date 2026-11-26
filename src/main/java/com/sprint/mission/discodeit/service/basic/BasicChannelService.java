package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.ChannelType;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {

    private final ChannelRepository repo;

    public BasicChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    @Override
    public Channel create(ChannelType type, String name, String description) {
        return repo.save(new Channel(type, name, description));
    }

    @Override
    public Channel read(UUID id) {
        return repo.findById(id);
    }

    @Override
    public List<Channel> readAll() {
        return repo.findAll();
    }

    @Override
    public void update(UUID id, String text) {
        Channel channel = repo.findById(id);
        if(channel != null){
            channel.updateName(text);
            repo.save(channel);
        }
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public List<Channel> findByText(String text) {
        return repo.findByName(text);
    }
}
