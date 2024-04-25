package com.iesam.digitallibrary.features.data;

import com.iesam.digitallibrary.features.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.domain.UserRepository;

public class UserDataRepository implements UserRepository {

    UserMemLocalDataSource userMemLocalDataSource = new UserMemLocalDataSource();

    public UserDataRepository(UserMemLocalDataSource userMemLocalDataSource) {

    }

    @Override
    public boolean createUser(User user) {
        this.userMemLocalDataSource.save(user);
        return false;
    }

}
