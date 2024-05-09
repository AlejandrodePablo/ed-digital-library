package com.iesam.digitallibrary.features.user.data.local;

import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.domain.User;

import java.util.*;

public class UserMemLocalDataSource {

    public static UserMemLocalDataSource instance = null;

    private Map<String, User> dataStore = new TreeMap<>();

    public UserMemLocalDataSource() {
        //Private Constructor
    }

    public static UserMemLocalDataSource getInstance() {
        if (instance == null) {
            instance = new UserMemLocalDataSource();
        }
        return instance;
    }

    public void save(User model) {
        dataStore.put(model.id, model);
    }

    public void saveList(List<User> models) {
        for (User User : models) {
            save(User);
        }
    }

    public User findById(String id) {
        return dataStore.get(id);
    }

    public List<User> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String modelId) {
        dataStore.remove(modelId);
    }

    public void update(User updatedUser) {
        if (dataStore.containsKey(updatedUser.id)) {
            dataStore.put(updatedUser.id, updatedUser);
            System.out.println("User updated successfully");
        } else {
            System.out.println("User with ID" + updatedUser.id + " does not exist");
        }
    }
}
