package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User save(User user); //create와 update를 save로 통합: Repository는 객체의 생성/수정 이유를 알 필요가 없음.

    List<User> findAll();

    User findById(UUID id);

    List<User> findByName(String name);

    void deleteById(UUID id);
}
