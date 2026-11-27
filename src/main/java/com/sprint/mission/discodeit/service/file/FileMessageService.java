package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileMessageService implements MessageService {
    private static final String DIRECTORY_NAME = "messages";
    private static final String PRE_FIX = "message_";
    private static final String POST_FIX = ".ser";
    private static FileMessageService instance;

    private FileMessageService() {
    }

    public static FileMessageService getInstance() {
        return (instance == null) ? instance = new FileMessageService() : instance;
    }

    /// / MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        Message entity = new Message(content, authorId, channelId);
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
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        Message entity = findById(id).update(content, authorId, channelId);
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
