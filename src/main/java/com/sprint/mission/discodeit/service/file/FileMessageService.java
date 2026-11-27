package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();
    private static final String DIRECTORY_NAME = "messages";
    private static final String EXTENSION = ".ser";


    /// / MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        Message entity = new Message(content, authorId, channelId);
        return SerializedFileStore.serializeToFile(entity, "messages", entity.getId(), ".ser");
    }

    @Override
    public Message findById(UUID id) {
        return getCheckedData(id);
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(this::getCheckedData)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        return getCheckedData(id).update(content, authorId, channelId);
    }

    @Override
    public void delete(UUID id) {
        getCheckedData(id);
        data.remove(id);
    }

    private Message getCheckedData(UUID id) {
        if (!data.containsKey(id)) {
            throw new IllegalArgumentException("유효하지 않은 Message Id. (" + id + ")");
        }
        return data.get(id);
    }
}
