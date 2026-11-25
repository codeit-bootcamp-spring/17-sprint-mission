package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFMessageService implements MessageService {
    private final List<Message> data;

    public JCFMessageService(){
        data = new ArrayList<>();
    }

    @Override
    public boolean create(String text) {
        return data.add(new Message(text));
    }

    @Override
    public Message read(UUID id) {
        return data.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Message> readAll() {
        return List.copyOf(data);
    }

    @Override
    public void update(UUID id, String text) {
        Message message = read(id);
        if(message != null) message.updateText(text);
    }

    @Override
    public void delete(UUID id) {
        Message message = read(id);
        if(message !=null) data.remove(message);
    }

    @Override
    public List<Message> findByText(String text) {
        return data.stream()
                .filter(x -> x.getText().equals(text))
                .collect(Collectors.toList());
    }
}
