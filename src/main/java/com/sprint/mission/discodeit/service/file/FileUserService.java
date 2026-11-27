package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUserService implements UserService {
    private static final String DIRECTORY_NAME = "users";
    private static final String PRE_FIX = "user_";
    private static final String POST_FIX = ".ser";
    private static FileUserService instance;

    private FileUserService() {
    }

    public static FileUserService getInstance() {
        return (instance == null) ? instance = new FileUserService() : instance;
    }

    /// / UserService Interface Override (CRUD)

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        User entity = new User(name, imageUrl, email, password);
        return SerializedFileStore.serialize(entity, getPath(entity.getId()));
    }

    @Override
    public User findById(UUID id) {
        return SerializedFileStore.deserialize(User.class, getPath(id));
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return ids.stream()
                .distinct()
                .map(this::getPath)
                .map(path -> SerializedFileStore.deserialize(User.class, path))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return SerializedFileStore.findAllByDirectory(getPath()).stream()
                .filter(path -> path.getFileName().toString().startsWith(PRE_FIX))
                .filter(path -> path.getFileName().toString().endsWith(POST_FIX))
                .map(path -> SerializedFileStore.deserialize(User.class, path))
                .collect(Collectors.toList());
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        User entity = findById(id).update(name, imageUrl, email, password);
        return SerializedFileStore.serialize(entity, getPath(id));
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(getPath(id));
    }

    private Path getPath() {
        return SerializedFileStore.DATA_HOME.resolve(DIRECTORY_NAME);
    }

    private Path getPath(UUID id) {
        return getPath().resolve(PRE_FIX + id + POST_FIX);
    }
}
