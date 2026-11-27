package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFUserRepository implements UserRepository {
    private final Map<UUID, User> data = new HashMap<>();
    private static JCFUserRepository instance;

    private JCFUserRepository() {
    }

    public static JCFUserRepository getInstance() {
        return (instance == null) ? instance = new JCFUserRepository() : instance;
    }

    @Override
    public User save(User entity) {
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public User findById(UUID id)  {
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
    public List<User> findAll()  {
        return data.values().stream().toList();
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
