package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();


    /// / ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String name, UUID serverId) {
        Channel newChannel = new Channel(name, serverId);
        data.put(newChannel.getId(), newChannel);
        return newChannel;
    }

    @Override
    public Channel findById(UUID id) {
        return getCheckedData(id);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(this::getCheckedData)
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
        if (!data.containsKey(id)) {
            throw new IllegalArgumentException("유효하지 않은 Channel Id. (" + id + ")");
        }
        return data.get(id);
    }
}
