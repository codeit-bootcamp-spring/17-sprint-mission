package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /// Create
    User create(String name, String imageUrl, String email, String password);

    /// Read
    User findById(UUID id);

    List<User> findAllById(List<UUID> ids);

    List<User> findAll();

    /// Update
    User update(UUID id, String name, String imageUrl, String email, String password);

    /// Delete
    void delete(UUID id);
}
