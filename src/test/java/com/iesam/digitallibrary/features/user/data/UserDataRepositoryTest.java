package com.iesam.digitallibrary.features.user.data;

import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDataRepositoryTest {

    @Mock
    UserMemLocalDataSource memLocalDataSource;

    @Mock
    UserFileLocalDataSource fileLocalDataSource;

    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        userRepository = new UserDataRepository(memLocalDataSource, fileLocalDataSource);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(memLocalDataSource, fileLocalDataSource);
    }

    @Test
    void createUserSuccessfully() {
        // Given
        User user = new User("123", "John", "Doe", "john@example.com", "30", "123456789");

        // When
        userRepository.createUser(user);

        // Then
        Mockito.verify(fileLocalDataSource, Mockito.times(1)).save(user);
        Mockito.verify(memLocalDataSource, Mockito.times(1)).save(user);
    }

    @Test
    void getUsersSuccessfully() {
        // Given
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("123", "John", "Doe", "john@example.com", "30", "123456789"));
        Mockito.when(memLocalDataSource.findAll()).thenReturn(expectedUsers);

        // When
        List<User> users = userRepository.getUsers();

        // Then
        Assertions.assertEquals(expectedUsers, users);
    }
    @Test
    void getUserSuccessfully() {
        // Given
        String userId = "123";
        User expectedUser = new User(userId, "John", "Doe", "john@example.com", "30", "123456789");
        Mockito.when(memLocalDataSource.findById(userId)).thenReturn(expectedUser);

        // When
        User user = userRepository.getUser(userId);

        // Then
        assertEquals(expectedUser, user);
    }

    @Test
    void deleteUserSuccessfully() {
        // Given
        String userId = "123";

        // When
        userRepository.deleteUser(userId);

        // Then
        Mockito.verify(fileLocalDataSource, Mockito.times(1)).delete(userId);
        Mockito.verify(memLocalDataSource, Mockito.times(1)).delete(userId);
    }

    @Test
    void updateUserSuccessfully() {
        // Given
        String userId = "123";
        User updatedUser = new User(userId, "Jane", "Doe", "jane@example.com", "35", "987654321");

        // When
        userRepository.updateUser(updatedUser);

        // Then
        Mockito.verify(fileLocalDataSource, Mockito.times(1)).update(updatedUser);
        Mockito.verify(memLocalDataSource, Mockito.times(1)).update(updatedUser);
    }

}