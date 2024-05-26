package com.iesam.digitallibrary.features.user.data;

import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

import java.util.List;

public class UserDataRepository implements UserRepository {

    private final UserMemLocalDataSource userMemLocalDataSource;
    private final UserFileLocalDataSource userFileLocalDataSource;

    public UserDataRepository(UserMemLocalDataSource userMemLocalDataSource, UserFileLocalDataSource userFileLocalDataSource) {
        this.userMemLocalDataSource = userMemLocalDataSource;
        this.userFileLocalDataSource = userFileLocalDataSource;
    }

    @Override
    public void createUser(User user) {
        userFileLocalDataSource.save(user);
        userMemLocalDataSource.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userMemLocalDataSource.findAll();
        if (users.isEmpty()) {
            users = userFileLocalDataSource.findAll();
            if (!users.isEmpty()) {
                userMemLocalDataSource.saveList(users);
            }
        }
        return users;
    }

    @Override
    public User getUser(String id) {
        User user = userMemLocalDataSource.findById(id);
        if (user == null) {
            user = userFileLocalDataSource.findById(id);
            if (user != null) {
                userMemLocalDataSource.save(user);
            }
        }
        return user;
    }

    @Override
    public void deleteUser(String id) {
        userFileLocalDataSource.delete(id);
        userMemLocalDataSource.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userFileLocalDataSource.update(user);
        userMemLocalDataSource.update(user);
    }
}
