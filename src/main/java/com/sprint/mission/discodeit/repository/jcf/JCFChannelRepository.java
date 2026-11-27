package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFChannelRepository implements ChannelRepository {
    private final Map<UUID, Channel> data = new HashMap<>();
    private static JCFChannelRepository instance;

    private JCFChannelRepository() {
    }

    public static JCFChannelRepository getInstance() {
        return (instance == null) ? instance = new JCFChannelRepository() : instance;
    }

    @Override
    public Channel save(Channel entity) {
        data.put(entity.getId(), entity);
        return entity;
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
