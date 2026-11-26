package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUserRepository implements UserRepository {
    private final Path directory;

    public FileUserRepository() {
        directory = Paths.get(System.getProperty("user.dir"), "data", "users");
        IOUtils.init(directory);
    }


    @Override
    public User save(User user) {
        IOUtils.save(IOUtils.getFilePath(directory, user.getId()), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return IOUtils.load(directory,User.class);
    }

    @Override
    public User findById(UUID id) {
        return IOUtils.loadOne(IOUtils.getFilePath(directory, id),User.class);
    }

    @Override
    public List<User> findByName(String name) {
        return findAll().stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory,id));
    }
}
