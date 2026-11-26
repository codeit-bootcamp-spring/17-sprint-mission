package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFChannelRepository implements ChannelRepository {
    private final Map<UUID, Channel> data;

    public JCFChannelRepository() {
        data = new HashMap<>();
    }

    @Override
    public Channel save(Channel channel) {
        data.put(channel.getId(),channel);
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public Channel findById(UUID id) {
        return data.get(id);
    }

    @Override
    public List<Channel> findByName(String name) {
        return data.values().stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        data.remove(id);
    }
}
