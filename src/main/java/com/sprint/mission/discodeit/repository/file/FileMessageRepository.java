package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileMessageRepository implements MessageRepository {
    private final Path directory;

    public FileMessageRepository() {
        directory = Paths.get(System.getProperty("user.dir","data"),"messages");
        IOUtils.init(directory);
    }

    @Override
    public Message save(Message message) {
        IOUtils.save(IOUtils.getFilePath(directory,message.getId()),message);
        return message;
    }

    @Override
    public List<Message> findAll() {
        return IOUtils.load(directory,Message.class);
    }

    @Override
    public Message findById(UUID id) {
        return IOUtils.loadOne(IOUtils.getFilePath(directory,id),Message.class);
    }

    @Override
    public List<Message> findByText(String text) {
        return findAll().stream()
                .filter(x -> x.getText().equals(text))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory,id));
    }
}
