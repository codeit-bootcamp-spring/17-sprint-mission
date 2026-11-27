package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.*;
import java.util.stream.Collectors;

public class JCFMessageRepository implements MessageRepository {
    private final Map<UUID, Message> data = new HashMap<>();
    private static JCFMessageRepository instance;

    private JCFMessageRepository() {
    }

    public static JCFMessageRepository getInstance() {
        return (instance == null) ? instance = new JCFMessageRepository() : instance;
    }

    @Override
    public Message save(Message entity) {
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Message findById(UUID id) {
        try {
            return getCheckedData(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(i -> {
                    try {
                        return getCheckedData(i);
                    } catch (NullPointerException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public void delete(UUID id) {
        getCheckedData(id);
        data.remove(id);
    }

    private Message getCheckedData(UUID id) {
        try {
            return data.get(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("유효하지 않은 id");
        }
    }
}
