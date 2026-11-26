package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository repo;
    private final UserService userService;
    private final ChannelService channelService;

    public BasicMessageService(MessageRepository repo, UserService userService, ChannelService channelService) {
        this.repo = repo;
        this.userService = userService;
        this.channelService = channelService;
    }

    private void validateId(UUID userId, UUID channelId){
        if (userService.read(userId) == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        if (channelService.read(channelId) == null) {
            throw new IllegalArgumentException("존재하지 않는 채널입니다.");
        }
    }

    @Override
    public Message create(UUID userId, UUID channelId, String text) {
        validateId(userId, channelId);
        Message message = new Message(userId,channelId,text);
        return repo.save(message);
    }

    @Override
    public Message read(UUID id) {
        return repo.findById(id);
    }

    @Override
    public List<Message> readAll() {
        return repo.findAll();
    }

    @Override
    public void update(UUID id, String text) {
        Message message = repo.findById(id);
        if(message != null){
            message.updateText(text);
            repo.save(message);
        }
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public List<Message> findByText(String text) {
        return repo.findByText(text);
    }
}
