package com.iesam.digitallibrary.features.domain;

import com.iesam.digitallibrary.features.data.StubUserDataRepository;
import com.iesam.digitallibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewUserUseCaseTest {

    private StubUserDataRepository stubUserDataRepository;

    @BeforeEach
    void setUp() {
        stubUserDataRepository = new StubUserDataRepository();
    }

    @AfterEach
    void tearDown() {
        stubUserDataRepository = null;
    }

    @Test
    public void ifUserIsNullThenReturnFalse() {
        //Given
        User user = null;
        //When
        boolean userNull = stubUserDataRepository.createUser(user);
        //Then
        Assertions.assertFalse(userNull);
    }

    @Test
    public void ifAnyUserValueIsNullThenReturnFalse() {
        //Given
        User userWithNulls = new User(null, null, null, null, null, null, null);
        //When
        boolean userWithNullsResults = stubUserDataRepository.createUser(userWithNulls);
        //Then
        Assertions.assertFalse(userWithNullsResults);
    }

}