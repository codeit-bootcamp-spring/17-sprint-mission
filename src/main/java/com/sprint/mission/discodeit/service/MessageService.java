package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(String name);
    Message read(UUID id);
    List<Message> readAll();
    void update(UUID id, String name);
    void delete(UUID id);
    List<Message> findByName(String name);
}
