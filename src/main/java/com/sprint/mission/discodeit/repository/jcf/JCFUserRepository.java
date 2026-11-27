package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.*;
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
    public User findById(UUID id) {
        try {
            return getCheckedData(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return ids.stream()
                .map(i -> {
                    try {
                        return getCheckedData(i);
                    } catch (NullPointerException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public void delete(UUID id) {
        getCheckedData(id);
        data.remove(id);
    }

    private User getCheckedData(UUID id) {
        try {
            return data.get(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("유효하지 않은 id");
        }
    }
}
