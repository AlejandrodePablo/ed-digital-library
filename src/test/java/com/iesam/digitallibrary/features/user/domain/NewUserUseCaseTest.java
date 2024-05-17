package com.iesam.digitallibrary.features.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewUserUseCaseTest {

    @Mock
    UserRepository userRepository;

    NewUserUseCase newUserUseCase;

    @BeforeEach
    void setUp() {
        newUserUseCase = new NewUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        newUserUseCase = null;
    }

    @Test
    public void receiveAnUserAndSaveIt() {
        //Given
        User userToSave = new User("10", "00000000A", "Prueba", "Guardado", "pg@email", "10", "999999999");

        //When
        newUserUseCase.execute(userToSave);

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).createUser(userToSave);
    }

    @Test
    public void ifNewUserIsNullThenNeverCreateANewUser() {
        // Given
        User nullUser = null;

        // When
        newUserUseCase.execute(nullUser);

        // Then
        Mockito.verify(userRepository, Mockito.never()).createUser(Mockito.any(User.class));
    }


}