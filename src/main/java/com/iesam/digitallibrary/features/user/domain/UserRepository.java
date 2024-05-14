package com.iesam.digitallibrary.features.user.domain;

import java.util.List;

public interface UserRepository {

    boolean createUser(User user);

    List<User> getUsers();

    User getUser(String id);

    void deleteUser(String id);

    void updateUser(User user);
}
