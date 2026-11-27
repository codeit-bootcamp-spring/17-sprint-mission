package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository repository;

    public BasicMessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        return repository.save(new Message(content, authorId, channelId));
    }

    @Override
    public Message findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        Message entity = repository.findById(id).update(content, authorId, channelId);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
