package com.iesam.digitallibrary.features.user.data;

import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

import java.util.List;

public class UserDataRepository implements UserRepository {

    //UserMemLocalDataSource userMemLocalDataSource = new UserMemLocalDataSource();
    UserFileLocalDataSource userFileLocalDataSource = new UserFileLocalDataSource();

    // public UserDataRepository(UserMemLocalDataSource userMemLocalDataSource) {    }
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
