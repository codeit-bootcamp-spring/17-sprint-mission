package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.*;
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
