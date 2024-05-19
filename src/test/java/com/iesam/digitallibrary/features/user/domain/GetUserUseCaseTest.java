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

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {

    @Mock
    UserRepository userRepository;

    GetUserUseCase getUserUseCase;

    @BeforeEach
    void setUp() {
        getUserUseCase = new GetUserUseCase(userRepository);
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

    @Test
    public void givingAnIdThatExistThenReturnAnUser(){
        //Given
        User userExpected = new User("100", "100","Prueba","Apellido","email@ejemplo","100", "000000000");
        Mockito.when(userRepository.getUser("100")).thenReturn(userExpected);

        //When
        User userReceived = getUserUseCase.execute("100");

        //Then
        Assertions.assertEquals(userReceived.id, "100");
        Assertions.assertEquals(userReceived.dni, "100");
        Assertions.assertEquals(userReceived.name, "Prueba");
        Assertions.assertEquals(userReceived.surname, "Apellido");
        Assertions.assertEquals(userReceived.email, "email@ejemplo");
        Assertions.assertEquals(userReceived.age, "100");
        Assertions.assertEquals(userReceived.telephone, "000000000");
    }

    @Test
    public void givingAnIdNotValidThenReturnNull(){
        //Given
        Mockito.when(userRepository.getUser("")).thenReturn(null);

        //When
        User userReceived = getUserUseCase.execute("");

        //Then
        Assertions.assertNull(userReceived);
    }
    /*
     *  This one do the same as ifUserIdDoesNotExistsThenReturnFalse()
     *  but without using the StubUserDataRepository class
     */

    @Test
    public void ifUserIdIsNullThenReturnNull(){
        //Given
        String userIdNull = null;
        Mockito.when(getUserUseCase.execute(userIdNull)).thenReturn(null);

        //When
        User userReceived = getUserUseCase.execute(userIdNull);

        //Then
        Assertions.assertNull(userReceived);
    }
}