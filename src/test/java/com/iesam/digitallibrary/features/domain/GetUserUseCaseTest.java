package com.iesam.digitallibrary.features.domain;

import com.iesam.digitallibrary.features.data.StubUserDataRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetUserUseCaseTest {

    private GetUserUseCase getUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        getUserUseCase = null;
    }

    @Test
    public void ifUserIdDoesNotExistsThenReturnFalse() {
        //Given
        getUserUseCase = new GetUserUseCase(new StubUserDataRepository());

        //When
        User getUser = getUserUseCase.execute(null);

        //Then
        Assertions.assertNull(getUser);
    }

}