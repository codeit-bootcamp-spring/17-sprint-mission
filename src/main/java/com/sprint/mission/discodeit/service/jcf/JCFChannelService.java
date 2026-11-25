package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFChannelService implements ChannelService {
    private final List<Channel> data;

    public JCFChannelService(){
        data = new ArrayList<>();
    }

    @Override
    public Channel create(String name) {
        Channel channel = new Channel(name);
        data.add(new Channel(name));
        return channel;
    }

    @Override
    public Channel read(UUID id) {
        return data.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Channel> readAll() {
        return List.copyOf(data);
    }

    @Override
    public void update(UUID id, String name) {
        Channel channel = read(id);
        if(channel != null) channel.updateName(name);
    }

    @Override
    public void delete(UUID id) {
        Channel channel = read(id);
        if(channel !=null) data.remove(channel);
    }

    @Override
    public List<Channel> findByName(String name) {
        return data.stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }
}
