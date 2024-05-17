package com.iesam.digitallibrary.features.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseTest {

    @Mock
    UserRepository userRepository;

    UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        updateUserUseCase = new UpdateUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        updateUserUseCase = null;
    }

    @Test
    public void ifIdExistThenUpdateUser() {
        //Given
        User updatedUser = new User("100", "98765432D", "NewName", "NewLastName", "new@example.com", "90", "987654321");
        Mockito.doNothing().when(userRepository).updateUser(Mockito.any(User.class));

        // When
        updateUserUseCase.execute(updatedUser);

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).updateUser(Mockito.any(User.class));
    }

    @Test
    public void ifUserIsNotValidThenNoUpdate(){
        //Given
        User invalidUser = null;

        //When
        updateUserUseCase.execute(invalidUser);

        //Then
        Mockito.verify(userRepository, Mockito.never()).updateUser(Mockito.any(User.class));
    }
}