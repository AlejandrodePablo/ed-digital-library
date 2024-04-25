package com.iesam.digitallibrary.features.data;

import com.iesam.digitallibrary.features.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.domain.UserRepository;

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

}
