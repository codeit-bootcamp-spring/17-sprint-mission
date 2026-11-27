package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    static final String DIRECTORY_NAME = "messages";
    static final String PRE_FIX = "message_";
    static final String POST_FIX = ".ser";

    Message save(Message entity);

    Message findById(UUID id);

    List<Message> findAllById(List<UUID> ids);

    List<Message> findAll();

    void delete(UUID id);

}
