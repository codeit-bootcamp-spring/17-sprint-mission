package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class FileChannelService implements ChannelService {
    private static final String DIRECTORY_NAME = "channels";
    private static final String PRE_FIX = "channel_";
    private static final String POST_FIX = ".ser";

    /// / ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String name, UUID serverId) {
        Channel entity = new Channel(name, serverId);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, entity.getId(), POST_FIX);
    }

    @Override
    public Channel findById(UUID id) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME, PRE_FIX, ids, POST_FIX);
    }

    @Override
    public List<Channel> findAll() {
        return SerializedFileStore.deserializeAllFromDirectory(Channel.class, DIRECTORY_NAME, PRE_FIX, POST_FIX);
    }

    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        Channel entity = findById(id).update(name,serverId);
        delete(id);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }
}
