package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileChannelRepository implements ChannelRepository {
    private final Path directory;

    public FileChannelRepository() {
        directory = Paths.get(System.getProperty("user.dir","data"),"channels");
        IOUtils.init(directory);
    }

    @Override
    public Channel save(Channel channel) {
        IOUtils.save(IOUtils.getFilePath(directory,channel.getId()),channel);
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return IOUtils.load(directory, Channel.class);
    }

    @Override
    public Channel findById(UUID id) {
        return IOUtils.loadOne(IOUtils.getFilePath(directory,id),Channel.class);
    }

    @Override
    public List<Channel> findByName(String name) {
        return findAll().stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory,id));
    }
}
