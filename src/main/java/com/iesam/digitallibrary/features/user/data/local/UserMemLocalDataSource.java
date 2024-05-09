package com.iesam.digitallibrary.features.user.data.local;

import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.domain.User;

import java.util.*;

public class UserMemLocalDataSource {

    // public static UserDataRepository instance = null;
    private UserDataRepository userDataRepository;

    /*
    public UserMemLocalDataSource() {
        userDataRepository = new UserDataRepository();
    }
     */
    private Map<String, User> dataStore = new TreeMap<>();

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

   /*
    public static UserDataRepository newInstance() {
        if (instance == null) {
            instance = new UserDataRepository();
        }
        return instance;
    }
    */
}