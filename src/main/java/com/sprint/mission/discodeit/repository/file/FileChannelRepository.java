package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileChannelRepository implements ChannelRepository {
    private static FileChannelRepository instance;

    private FileChannelRepository() {
    }

    public static FileChannelRepository getInstance() {
        return (instance == null) ? instance = new FileChannelRepository() : instance;
    }

    @Override
    public Channel save(Channel entity) {
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
                .collect(Collectors.toList());
    }

    @Override
    public List<Channel> findAll() {
        return SerializedFileStore.findAllByDirectory(getPath()).stream()
                .filter(path -> path.getFileName().toString().startsWith(PRE_FIX))
                .filter(path -> path.getFileName().toString().endsWith(POST_FIX))
                .map(path -> SerializedFileStore.deserialize(Channel.class, path))
                .collect(Collectors.toList());
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
