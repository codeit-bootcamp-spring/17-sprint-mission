package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUserService implements UserService {
    private final Map<UUID, User> data = new HashMap<>();


    /// / UserService Interface Override (CRUD)

    @Override
    public User create(String name, String imageUrl, String email, String password) {
        User newUser = new User(name, imageUrl, email, password);
        data.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public User findById(UUID id) {
        return getCheckedData(id);
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(this::getCheckedData)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public User update(UUID id, String name, String imageUrl, String email, String password) {
        return getCheckedData(id).update(name, imageUrl, email, password);
    }

    @Override
    public void delete(UUID id) {
        getCheckedData(id);
        data.remove(id);
    }

    private User getCheckedData(UUID id) {
        if (!data.containsKey(id)) {
            throw new IllegalArgumentException("유효하지 않은 User Id. (" + id + ")");
        }
        return data.get(id);
    }
}
