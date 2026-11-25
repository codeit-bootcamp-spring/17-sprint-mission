package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileMessageService implements MessageService {
    private final Path directory;
    private final UserService userService;
    private final ChannelService channelService;

    public FileMessageService(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
        this.directory = Paths.get(System.getProperty("user.dir"), "data", "message");
        IOUtils.init(directory);
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
        IOUtils.save(IOUtils.getFilePath(directory, message.getId()), message);
        return message;
    }

    @Override
    public Message read(UUID id) {
        return readAll().stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Message> readAll() {
        return IOUtils.load(directory,Message.class);
    }

    @Override
    public void update(UUID id, String text) {
        Message message = read(id);
        if(message != null){
            message.updateText(text);
            IOUtils.save(IOUtils.getFilePath(directory, id), message);
        }
    }

    @Override
    public void delete(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory,id));
    }

    @Override
    public List<Message> findByText(String text) {
        return readAll().stream()
                .filter(x -> x.getText().equals(text))
                .toList();
    }
}
