package com.iesam.digitallibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;

    DeleteLoanUseCase deleteLoanUseCase;

    @BeforeEach
    void setUp() {
        deleteLoanUseCase = new DeleteLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        deleteLoanUseCase = null;
    }

    @Test
    public void ifALoanIdIsValidThenDeleteLoan() {
        //Given
        String validId = "100";
        Mockito.doNothing().when(loanRepository).deleteLoan(validId);

        //When
        deleteLoanUseCase.execute(validId);

        //Then
        Mockito.verify(loanRepository, Mockito.times(1)).deleteLoan(validId);
    }

    @Test
    public void givingAnInvalidIdThenDoesNotDeleteLoan() {
        //Given
        String invalidId = "";
        Mockito.doNothing().when(loanRepository).deleteLoan(invalidId);

        //When
        deleteLoanUseCase.execute(invalidId);

    //Then
        Mockito.verify(loanRepository, Mockito.times(1)).deleteLoan(invalidId);
    }

    @Test
    public void givingNullIdThenNoDeleteAnyLoan() {
        //Given
        String nullId = null;

        //When
        deleteLoanUseCase.execute(nullId);

        //Then
        Mockito.verify(loanRepository, Mockito.never()).deleteLoan(Mockito.anyString());
    }
}