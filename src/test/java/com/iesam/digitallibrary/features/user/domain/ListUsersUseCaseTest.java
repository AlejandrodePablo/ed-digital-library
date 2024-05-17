package com.iesam.digitallibrary.features.user.domain;

import com.iesam.digitallibrary.features.user.data.StubUserDataRepository;
import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListUsersUseCaseTest {

    @Mock
    UserRepository userRepository;

    ListUsersUseCase listUsersUseCase;

    @BeforeEach
    void setUp() {
        listUsersUseCase = new ListUsersUseCase(userRepository);
    }

    @AfterEach
    void tearDown() {
        listUsersUseCase = null;
    }

    @Test
    public void ifUserListIsEmptyThenReturnFalse() {
        // Given
        listUsersUseCase = new ListUsersUseCase(new StubUserDataRepository());

        // When
        List<User> userListEmpty = listUsersUseCase.execute();

        // Then
        Assertions.assertNull(userListEmpty);
    }

    /*
    Similar but with Mock
     */
    @Test
    public void whenUserRepositoryIsEmptyThenReturnEmptyList(){
        //Given
        Mockito.when(userRepository.getUsers()).thenReturn(Collections.emptyList());

        //When
        List<User> userList = listUsersUseCase.execute();

        //Then
        Assertions.assertTrue(userList.isEmpty());
    }
    @Test
    public void whenUserRepositoryContainsUsersThenReturnUserList(){
        //Given
        User user1 = new User("1", "12345678A", "Name1", "One", "one@example.com", "30", "123456789");
        User user2 = new User("2", "87654321B", "Name2", "Two", "two@example.com", "25", "987654321");
        Mockito.when(userRepository.getUsers()).thenReturn(List.of(user1, user2));

        //When
        List<User> userList = listUsersUseCase.execute();

        //Then
        Assertions.assertFalse(userList.isEmpty());
        Assertions.assertEquals(2, userList.size());
        Assertions.assertTrue(userList.contains(user1));
        Assertions.assertTrue(userList.contains(user2));
    }
}