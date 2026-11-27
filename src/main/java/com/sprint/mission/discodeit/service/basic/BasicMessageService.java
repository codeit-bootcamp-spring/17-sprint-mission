package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFChannelRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFUserRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private static BasicMessageService instance;

    private BasicMessageService() {
        messageRepository = FileMessageRepository.getInstance();
        userRepository =    FileUserRepository.getInstance();
        channelRepository = FileChannelRepository.getInstance();
    }

//    private BasicMessageService() {
//        messageRepository = JCFMessageRepository.getInstance();
//        userRepository = JCFUserRepository.getInstance();
//        channelRepository = JCFChannelRepository.getInstance();
//    }

    public static BasicMessageService getInstance() {
        return (instance == null) ? instance = new BasicMessageService() : instance;
    }

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        if (userRepository.findById(authorId) == null)
            return null;
        if (channelRepository.findById(channelId) == null)
            return null;

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
