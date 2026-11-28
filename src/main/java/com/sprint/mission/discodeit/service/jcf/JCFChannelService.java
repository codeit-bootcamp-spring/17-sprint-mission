package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;
import java.util.stream.Collectors;

public class JCFChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();
    private static JCFChannelService instance;

    private JCFChannelService() {
    }

    public static JCFChannelService getInstance() {
        return (instance == null) ? instance = new JCFChannelService() : instance;
    }

    /// ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String name, UUID serverId) {
        Channel newChannel = new Channel(name, serverId);
        data.put(newChannel.getId(), newChannel);
        return newChannel;
    }

    @Override
    public Channel findById(UUID id) {
        try {
            return getCheckedData(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(i -> {
                    try {
                        return getCheckedData(i);
                    } catch (NullPointerException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Channel> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        return getCheckedData(id).update(name, serverId);
    }

    @Override
    public void delete(UUID id) {
        getCheckedData(id);
        data.remove(id);
    }

    private Channel getCheckedData(UUID id) {
        try {
            return data.get(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("유효하지 않은 id");
        }
    }
}
