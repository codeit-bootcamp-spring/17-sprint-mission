package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    private static BasicUserService instance;

    private BasicUserService() {
        userRepository = FileUserRepository.getInstance();
//        repository = JCFUserRepository.getInstance();
    }

    public static BasicUserService getInstance() {
        return (instance == null) ? instance = new BasicUserService() : instance;
    }

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        return userRepository.save(new User(name, imageUrl, email, password));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        User entity = userRepository.findById(id).update(name, imageUrl, email, password);
        return userRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
