package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFMessageRepository implements MessageRepository {
    private final Map<UUID, Message> data = new HashMap<>();

    @Override
    public Message save(Message entity) {
        data.put(entity.getId(), entity);
        return entity;
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
