package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class FileMessageService implements MessageService {
    private static final String DIRECTORY_NAME = "messages";
    private static final String PRE_FIX = "message_";
    private static final String POST_FIX = ".ser";

    /// / MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        Message entity = new Message(content, authorId, channelId);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, entity.getId(), POST_FIX);
    }

    @Override
    public Message findById(UUID id) {
        return SerializedFileStore.deserializeFromFile(Message.class, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return SerializedFileStore.deserializeFromFile(Message.class, DIRECTORY_NAME, PRE_FIX, ids, POST_FIX);
    }

    @Override
    public List<Message> findAll() {
        return SerializedFileStore.deserializeAllFromDirectory(Message.class, DIRECTORY_NAME, PRE_FIX, POST_FIX);
    }

    @Override
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        Message entity = findById(id).update(content, authorId, channelId);
        delete(id);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }
}
