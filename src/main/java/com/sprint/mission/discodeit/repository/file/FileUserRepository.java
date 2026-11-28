package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.config.SerializedFileStore;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUserRepository implements UserRepository {
    private static FileUserRepository instance;

    private FileUserRepository() {
    }

    public static FileUserRepository getInstance() {
        return (instance == null) ? instance = new FileUserRepository() : instance;
    }

    @Override
    public User save(User entity) {
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
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return SerializedFileStore.findAllByDirectory(getPath()).stream()
                .filter(path -> path.getFileName().toString().startsWith(PRE_FIX))
                .filter(path -> path.getFileName().toString().endsWith(POST_FIX))
                .map(path -> SerializedFileStore.deserialize(User.class, path))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
