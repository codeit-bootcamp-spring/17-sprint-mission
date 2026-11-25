package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFUserService implements UserService {
    private final List<User> data;

    public JCFUserService() {
        data = new ArrayList<>();
    }


    @Override
    public User create(String name) {
        User user = new User(name);
        data.add(user);
        return user;
    }

    @Override
    public User read(UUID id) {
        return data.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> readAll() {
        return List.copyOf(data); //ai제안: 수정을 막기 위함
    }

    @Override
    public void update(UUID id, String name) {
        User user = read(id);
        if(user != null) user.updateName(name);
    }

    @Override
    public void delete(UUID id) {
        User user = read(id);
        if(user != null) data.remove(user);
    }

    @Override
    public List<User> findByName(String name) {
        return data.stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }
}
