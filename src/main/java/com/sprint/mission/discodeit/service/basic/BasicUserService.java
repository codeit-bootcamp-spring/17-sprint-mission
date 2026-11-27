package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicUserService implements UserService {
    private final UserRepository repository;

    public BasicUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        return repository.save(new User(name, imageUrl, email, password));
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        User entity = repository.findById(id).update(name, imageUrl, email, password);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
