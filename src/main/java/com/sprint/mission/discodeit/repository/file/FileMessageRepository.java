package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.List;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    @Override
    public Message save(Message entity) {
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX,  entity.getId(), POST_FIX);
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
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX,  id, POST_FIX);
    }
}
