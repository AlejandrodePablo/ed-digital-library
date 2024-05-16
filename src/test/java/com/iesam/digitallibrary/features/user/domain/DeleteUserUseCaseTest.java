package com.iesam.digitallibrary.features.user.domain;

import com.iesam.digitallibrary.features.user.data.StubUserDataRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DeleteUserUseCaseTest {

    private DeleteUserUseCase deleteUserUseCase;
    private StubUserDataRepository stubUserDataRepository;

    @BeforeEach
    void setUp() {
        stubUserDataRepository = new StubUserDataRepository();
        deleteUserUseCase = new DeleteUserUseCase(stubUserDataRepository);
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
}