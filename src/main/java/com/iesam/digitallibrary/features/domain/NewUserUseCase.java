package com.iesam.digitallibrary.features.domain;

public class NewUserUseCase {

    private UserRepository userRepository;

    public NewUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        this.userRepository.createUser(user);
    }
}
