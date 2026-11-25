package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    boolean create(String name);
    Message read(UUID id);
    List<Message> readAll();
    void update(UUID id, String name);
    void delete(UUID id);
    List<Message> findByText(String name);
}
