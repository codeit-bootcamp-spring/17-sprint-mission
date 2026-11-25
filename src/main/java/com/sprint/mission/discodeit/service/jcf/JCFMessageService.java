package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();


    //// MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        return null;
    }

    @Override
    public Message findById(UUID id) {
        return null;
    }

    @Override
    public List<Message> findAllById(UUID... ids) {
        return List.of();
    }

    @Override
    public List<Message> findAll() {
        return List.of();
    }

    @Override
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
