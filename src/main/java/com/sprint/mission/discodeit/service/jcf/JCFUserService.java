package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {
    private final Map<UUID, User> data = new HashMap<>();


    //// UserService Interface Override (CRUD)

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public List<User> findAllById(UUID... ids) {
        return List.of();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
