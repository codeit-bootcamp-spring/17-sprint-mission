package com.sprint.mission.discodeit.service;

import com.sprint.mission.ChannelType;
import com.sprint.mission.discodeit.entity.Channel;
import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(ChannelType type, String name, String description);
    Channel read(UUID id);
    List<Channel> readAll();
    void update(UUID id, String name);
    void delete(UUID id);
    List<Channel> findByText(String text);
}
