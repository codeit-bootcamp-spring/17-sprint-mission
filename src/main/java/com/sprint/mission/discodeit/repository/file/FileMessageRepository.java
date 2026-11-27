package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileMessageRepository implements MessageRepository {
    private static FileMessageRepository instance;

    private FileMessageRepository() {
    }

    public static FileMessageRepository getInstance() {
        return (instance == null) ? instance = new FileMessageRepository() : instance;
    }

    @Override
    public Message save(Message entity) {
        return SerializedFileStore.serialize(entity, getPath(entity.getId()));
    }

    @Override
    public Message findById(UUID id) {
        return SerializedFileStore.deserialize(Message.class, getPath(id));
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return ids.stream()
                .distinct()
                .map(this::getPath)
                .map(path -> SerializedFileStore.deserialize(Message.class, path))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findAll() {
        return SerializedFileStore.findAllByDirectory(getPath()).stream()
                .filter(path -> path.getFileName().toString().startsWith(PRE_FIX))
                .filter(path -> path.getFileName().toString().endsWith(POST_FIX))
                .map(path -> SerializedFileStore.deserialize(Message.class, path))
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
