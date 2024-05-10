package com.iesam.digitallibrary.features.user.data;

import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

import java.util.List;

public class UserDataRepository implements UserRepository {

    public static final UserMemLocalDataSource userMemLocalDataSource = UserMemLocalDataSource.getInstance();
    public static final UserFileLocalDataSource userFileLocalDataSource = UserFileLocalDataSource.getInstance();


    public UserDataRepository(UserMemLocalDataSource userDataSource) {
    }

    public UserDataRepository(UserFileLocalDataSource userFileLocalDataSource) {
    }

    @Override
    public boolean createUser(User user) {
        this.userFileLocalDataSource.save(user);
        return false;
    }

    @Override
    public List<User> getUsers() {
        return userFileLocalDataSource.findAll();
    }

    @Override
    public User getUser(String id) {
        return userFileLocalDataSource.findById(id);
    }

    @Override
    public void deleteUser(String id) {
        this.userFileLocalDataSource.delete(id);
    }

    @Override
    public void updateUser(User user) {
        this.userFileLocalDataSource.update(user);
    }
}
