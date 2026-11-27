package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class JCFUserRepository implements UserRepository {
    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return List.of();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
