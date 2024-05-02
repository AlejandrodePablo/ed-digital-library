package com.iesam.digitallibrary.features.domain;

public class DeleteUserUseCase {

    private UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String id) {
        this.userRepository.deleteUser(id);
    }
}
