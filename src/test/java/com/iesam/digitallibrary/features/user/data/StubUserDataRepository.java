package com.iesam.digitallibrary.features.user.data;

import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class StubUserDataRepository implements UserRepository {

    private List<User> userList = new ArrayList<>();

    @Override
    public void createUser(User user) {
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void updateUser(User user) {

    }


}
