package com.iesam.digitallibrary.features.domain;

import com.iesam.digitallibrary.features.data.StubUserDataRepository;
import com.iesam.digitallibrary.features.user.domain.ListUsersUseCase;
import com.iesam.digitallibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListUsersUseCaseTest {

    private ListUsersUseCase listUsersUseCase;

    @BeforeEach
    void setUp() {
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
}