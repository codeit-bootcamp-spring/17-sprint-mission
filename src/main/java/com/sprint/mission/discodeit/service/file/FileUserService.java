package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class FileUserService implements UserService {
    private static final String DIRECTORY_NAME = "users";
    private static final String PRE_FIX = "user_";
    private static final String POST_FIX = ".ser";

    /// / UserService Interface Override (CRUD)

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        User entity = new User(name, imageUrl, email, password);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, entity.getId(), POST_FIX);
    }

    @Override
    public User findById(UUID id) {
        return SerializedFileStore.deserializeFromFile(User.class, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return SerializedFileStore.deserializeFromFile(User.class, DIRECTORY_NAME, PRE_FIX, ids, POST_FIX);
    }

    @Override
    public List<User> findAll() {
        return SerializedFileStore.deserializeAllFromDirectory(User.class, DIRECTORY_NAME, PRE_FIX, POST_FIX);
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        User entity = findById(id).update(name, imageUrl, email, password);
        return SerializedFileStore.serializeToFile(entity, DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }

    @Override
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }
}
