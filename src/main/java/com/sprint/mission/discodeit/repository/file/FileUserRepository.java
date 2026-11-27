package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class FileUserRepository implements UserRepository {
    @Override
    public User save(User entity) {
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
    public void delete(UUID id) {
        SerializedFileStore.deleteFile(DIRECTORY_NAME, PRE_FIX, id, POST_FIX);
    }
}
