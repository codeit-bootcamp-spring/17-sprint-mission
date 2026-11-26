package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.ChannelType;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileChannelService  implements ChannelService{
    private final Path directory;

    public FileChannelService() {
        this.directory = Paths.get(System.getProperty("user.dir"), "data", "channel");
        IOUtils.init(directory);
    }

    @Override
    public Channel create(ChannelType type, String name, String description) {
        Channel channel = new Channel(type, name, description);
        IOUtils.save(IOUtils.getFilePath(directory, channel.getId()), channel);
        return channel;
    }

    @Override
    public Channel read(UUID id) {
        return readAll().stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Channel> readAll() {
        return IOUtils.load(directory,Channel.class);
    }

    @Override
    public void update(UUID id, String name) {
        Channel channel = read(id);
        if(channel != null){
            channel.updateName(name);
            IOUtils.save(IOUtils.getFilePath(directory, id), channel);
        }
    }

    @Override
    public void delete(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory, id));
    }

    @Override
    public List<Channel> findByText(String text) {
        return readAll().stream()
                .filter(x -> x.getName().equals(text))
                .toList();
    }
}
