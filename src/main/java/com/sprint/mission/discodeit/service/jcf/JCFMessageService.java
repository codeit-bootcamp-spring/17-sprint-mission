package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;
import java.util.stream.Collectors;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();
    private static JCFMessageService instance;

    private JCFMessageService() {
    }

    public static JCFMessageService getInstance() {
        return (instance == null) ? instance = new JCFMessageService() : instance;
    }

    /// MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        Message newMessage = new Message(content, authorId, channelId);
        data.put(newMessage.getId(), newMessage);
        return newMessage;
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
