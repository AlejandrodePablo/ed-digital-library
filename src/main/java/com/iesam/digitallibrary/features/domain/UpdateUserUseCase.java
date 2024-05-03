package com.iesam.digitallibrary.features.domain;

public class UpdateUserUseCase {

    private UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        this.userRepository.updateUser(user);
    }
}
