package com.iesam.digitallibrary.features.data;

import com.iesam.digitallibrary.features.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.domain.UserRepository;

public class UserDataRepository implements UserRepository {

    UserMemLocalDataSource userMemLocalDataSource = new UserMemLocalDataSource();
    @Override
    public void createUser(User user) {
        userMemLocalDataSource.save(user);
    }
}
