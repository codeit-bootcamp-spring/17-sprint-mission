package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;

    private static BasicChannelService instance;

    private BasicChannelService() {
        channelRepository = FileChannelRepository.getInstance();
//        repository = JCFChannelRepository.getInstance();
    }

    public static BasicChannelService getInstance() {
        return (instance == null) ? instance = new BasicChannelService() : instance;
    }

    @Override
    public Channel create(String name, UUID serverId) {
        return channelRepository.save(new Channel(name, serverId));
    }

    @Override
    public Channel findById(UUID id) {
        return channelRepository.findById(id);
    }

    @Override
    public List<Channel> findAllById(List<UUID> ids) {
        return channelRepository.findAllById(ids);
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel update(UUID id, String name, UUID serverId) {
        Channel entity = channelRepository.findById(id).update(name, serverId);
        return channelRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        channelRepository.delete(id);
    }
}
