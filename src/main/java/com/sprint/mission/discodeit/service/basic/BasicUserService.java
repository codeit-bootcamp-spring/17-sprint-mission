package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicUserService implements UserService {
    private final UserRepository repo;

    public BasicUserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(String name, String email, String nickname) {
        return repo.save(new User(name, email, nickname));
    }

    @Override
    public User read(UUID id) {
        return repo.findById(id);
    }

    @Override
    public List<User> readAll() {
        return repo.findAll();
    }

    @Override
    public void update(UUID id, String name) {
        User user = repo.findById(id);
        if(user != null){
            user.updateName(name);
            repo.save(user);
        }
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return repo.findByName(name);
    }
}
