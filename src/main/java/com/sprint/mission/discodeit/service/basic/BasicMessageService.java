package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;

    private static BasicMessageService instance;

    private BasicMessageService() {
        messageRepository = FileMessageRepository.getInstance();
//        repository = JCFMessageRepository.getInstance();
    }

    public static BasicMessageService getInstance() {
        return (instance == null) ? instance = new BasicMessageService() : instance;
    }

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        return messageRepository.save(new Message(content, authorId, channelId));
    }

    @Override
    public Message findById(UUID id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAllById(List<UUID> ids) {
        return messageRepository.findAllById(ids);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        Message entity = messageRepository.findById(id).update(content, authorId, channelId);
        return messageRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        messageRepository.delete(id);
    }
}
