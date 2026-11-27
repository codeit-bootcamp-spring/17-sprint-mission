package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.List;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    @Override
    public Channel save(Channel entity) {
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, entity.getId(), POST_FIX);
    }

    @Override
    public Channel findById(UUID id) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return SerializedFileStore.deserializeFromFile(Channel.class, DIRECTORY_NAME,PRE_FIX,  ids, POST_FIX);
    }

    @Override
    public List<Channel> findAll() {
        return SerializedFileStore.deserializeAllFromDirectory(Channel.class, DIRECTORY_NAME, PRE_FIX, POST_FIX);
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }
}
