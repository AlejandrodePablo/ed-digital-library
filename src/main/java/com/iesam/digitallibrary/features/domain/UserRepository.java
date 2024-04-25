package com.iesam.digitallibrary.features.domain;

import java.util.List;

public interface UserRepository {

    boolean createUser(User user);

    List<User> getUsers();

    User getUser(String id);
}
