package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    User save(User entity);

    User findById(UUID id);

    List<User> findAllById(List<UUID> ids);

    List<User> findAll();

    void delete(UUID id);
}
