package com.iesam.digitallibrary.features.data;

import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.domain.UserRepository;

public class StubUserDataRepository implements UserRepository {


    @Override
    public boolean createUser(User user) {
        return false;
    }
}
