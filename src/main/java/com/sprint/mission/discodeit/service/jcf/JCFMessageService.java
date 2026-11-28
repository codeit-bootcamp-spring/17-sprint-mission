package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();
    private static JCFMessageService instance;
    private final ChannelService channelService;
    private final UserService userService;

    private JCFMessageService() {
        channelService = JCFChannelService.getInstance();
        userService = JCFUserService.getInstance();
    }

    public static JCFMessageService getInstance() {
        return (instance == null) ? instance = new JCFMessageService() : instance;
    }

    /// MessageService Interface Override (CRUD)

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        if (userService.findById(authorId) == null)
            return null;
        if (channelService.findById(channelId) == null)
            return null;

        Message newMessage = new Message(content, authorId, channelId);
        data.put(newMessage.getId(), newMessage);
        return newMessage;
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
    public Message update(UUID id, String content, UUID authorId, UUID channelId) {
        return getCheckedData(id).update(content, authorId, channelId);
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
