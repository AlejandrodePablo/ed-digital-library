package com.iesam.digitallibrary.features.user.domain;

import java.util.List;

public class ListUsersUseCase {

    private UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.getUsers();
    }
}
