package com.iesam.digitallibrary.features.data;

import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class StubUserDataRepository implements UserRepository {

    private List<User> userList = new ArrayList<>();

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
