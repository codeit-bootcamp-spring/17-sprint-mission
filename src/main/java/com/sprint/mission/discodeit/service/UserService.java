package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(String name); //File*Service에서 boolean이 의미가 없어져서 다시 바꿈
    User read(UUID id);
    List<User> readAll();
    void update(UUID id, String name);
    void delete(UUID id);
    List<User> findByName(String name);
}
