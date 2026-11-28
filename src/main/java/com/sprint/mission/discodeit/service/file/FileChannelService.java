package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileChannelService implements ChannelService {
    private static final String DIRECTORY_NAME = "channels";
    private static final String PRE_FIX = "channel_";
    private static final String POST_FIX = ".ser";
    private static FileChannelService instance;

    private FileChannelService() {
    }

    public static FileChannelService getInstance() {
        return (instance == null) ? instance = new FileChannelService() : instance;
    }

    /// / ChannelService Interface Override (CRUD)

    @Override
    public Channel create(String name, UUID serverId) {
        Channel entity = new Channel(name, serverId);
        return SerializedFileStore.serialize(entity, getPath(entity.getId()));
    }

    @Override
    public Channel findById(UUID id) {
        return SerializedFileStore.deserialize(Channel.class, getPath(id));
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return ids.stream()
                .distinct()
                .map(this::getPath)
                .map(path -> SerializedFileStore.deserialize(Channel.class, path))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Channel> findAll() {
        return SerializedFileStore.findAllByDirectory(getPath()).stream()
                .filter(path -> path.getFileName().toString().startsWith(PRE_FIX))
                .filter(path -> path.getFileName().toString().endsWith(POST_FIX))
                .map(path -> SerializedFileStore.deserialize(Channel.class, path))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        Channel entity = findById(id).update(name, serverId);
        return SerializedFileStore.serialize(entity, getPath(id));
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(getPath(id));
    }

    private Path getPath() {
        return SerializedFileStore.DATA_HOME.resolve(DIRECTORY_NAME);
    }

    private Path getPath(UUID id) {
        return getPath().resolve(PRE_FIX + id + POST_FIX);
    }
}
