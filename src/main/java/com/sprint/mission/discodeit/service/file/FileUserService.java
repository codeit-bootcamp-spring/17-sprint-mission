package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileUserService implements UserService {
    private final Path directory;

    public FileUserService() {
        this.directory = Paths.get(System.getProperty("user.dir"), "data", "user");
        IOUtils.init(directory);
    }

    @Override
    public User create(String name, String email, String nickname) {
        User user = new User(name, email, nickname);
        IOUtils.save(IOUtils.getFilePath(directory, user.getId()), user);
        return user;
    }

    @Override
    public User read(UUID id) {
        return readAll().stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> readAll() {
        return IOUtils.load(directory, User.class);
    }

    @Override
    public void update(UUID id, String name) {
        User user = read(id);
        if(user != null){
            user.updateName(name);
            IOUtils.save(IOUtils.getFilePath(directory,id), user);
        }
    }

    @Override
    public void delete(UUID id) {
        IOUtils.delete(IOUtils.getFilePath(directory,id));
    }

    @Override
    public List<User> findByName(String name) {
        return readAll().stream()
                .filter(x -> x.getName().equals(name))
                .toList();
    }
}
