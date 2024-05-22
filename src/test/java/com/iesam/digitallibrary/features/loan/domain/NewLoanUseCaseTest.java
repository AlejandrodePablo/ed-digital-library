package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class NewLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;

    NewLoanUseCase newLoanUseCase;

    @BeforeEach
    void setUp() {
        newLoanUseCase = new NewLoanUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        newLoanUseCase = null;
    }

    @Test
    void givenValidLoanThenSaveLoan() {
        // Given
        Loan validLoan = new Loan("123",
                new User(null,null,null,null,null,null,null),
                new EBook(null,null,null,null,null,null),
                "2024-05-25", "2024-06-05");

        // When
        newLoanUseCase.execute(validLoan);

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).createLoan(validLoan);
    }

    @Test
    public void ifLoanIsNullThenNeverCreateIt(){
        //Given
        Loan nullLoan = null;

        //When
        newLoanUseCase.execute(nullLoan);

        //Then
        Mockito.verify(loanRepository, Mockito.never()).createLoan(Mockito.any(Loan.class));
    }

}