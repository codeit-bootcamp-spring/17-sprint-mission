package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFMessageService implements MessageService {
    private final List<Message> data;
    private final ChannelService channelService;
    private final UserService userService;

    public JCFMessageService(ChannelService channelService, UserService userService){
        data = new ArrayList<>();
        this.channelService = channelService;
        this.userService = userService;
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
        Message message = new Message(userId, channelId, text);
        data.add(message);
        return message;
    }

    @Override
    public Message read(UUID id) {
        return data.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Message> readAll() {
        return List.copyOf(data);
    }

    @Override
    public void update(UUID id, String text) {
        Message message = read(id);
        if(message != null) message.updateText(text);
    }

    @Override
    public void delete(UUID id) {
        Message message = read(id);
        if(message !=null) data.remove(message);
    }

    @Override
    public List<Message> findByText(String text) {
        return data.stream()
                .filter(x -> x.getText().equals(text))
                .collect(Collectors.toList());
    }
}
