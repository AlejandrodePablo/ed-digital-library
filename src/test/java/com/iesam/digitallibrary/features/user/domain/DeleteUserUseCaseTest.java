package com.iesam.digitallibrary.features.user.domain;

import com.iesam.digitallibrary.features.user.data.StubUserDataRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    UserRepository userRepository;

    DeleteUserUseCase deleteUserUseCase;
    StubUserDataRepository stubUserDataRepository;

    @BeforeEach
    void setUp() {
        stubUserDataRepository = new StubUserDataRepository();
        deleteUserUseCase = new DeleteUserUseCase(stubUserDataRepository);
        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        deleteUserUseCase = null;
        stubUserDataRepository = null;
    }

    @Test
    public void ifUserIdDoesNotExistsThenReturnFalse() {
        // Given
        String userIdToDelete = "non_existent_id";

        // When
        deleteUserUseCase.execute(userIdToDelete);

        // Then
        assertNull(stubUserDataRepository.getUser(userIdToDelete));
    }

    @Test
    public void deleteAnUserThatExist() {
        //Given
        User userToDelete = new User("100", "98765432D", "Delete", "TestDelete", "td@example", "90", "987654321");
        Mockito.doNothing().when(userRepository).deleteUser("100");

        //When
        deleteUserUseCase.execute("100");

        //Then
        Mockito.verify(userRepository).deleteUser(userToDelete.id);
    }

    @Test
    public void whetherTheDeleteUserMethodIsBeingInvokedCorrectly() {
        //Given
        Mockito.doNothing().when(userRepository).deleteUser("100");

        //When
        deleteUserUseCase.execute("100");

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).deleteUser("100");
    }

    @Test
    public void ifIdIsNullThenItDoesNotTDelete() {
        // Given
        String userId = null;
        //Could be replaced giving null at deleteUserUseCase.execute(null)

        //When
        deleteUserUseCase.execute(userId);

        //
        Mockito.verify(userRepository, Mockito.never()).deleteUser(Mockito.anyString());
    }
}