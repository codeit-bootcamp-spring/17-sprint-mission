package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();
    private static final String DIRECTORY_NAME = "channels";
    private static final String EXTENSION = ".ser";

    /// / ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String name, UUID serverId) {
        Channel entity = new Channel(name, serverId);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, entity.getId(), EXTENSION);
    }

    @Override
    public Channel findById(UUID id) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME, id, EXTENSION);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME, ids, EXTENSION);
    }

    @Override
    public List<Channel> findAll() {
        return SerializedFileStore.deserializeAllFromDirectory(Channel.class, DIRECTORY_NAME, EXTENSION);
    }

    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        Channel channel = findById(id).update(name,serverId);
        delete(id);
        return SerializedFileStore.serializeToFile(channel, DIRECTORY_NAME, id, EXTENSION);
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, id, EXTENSION);
    }
}
