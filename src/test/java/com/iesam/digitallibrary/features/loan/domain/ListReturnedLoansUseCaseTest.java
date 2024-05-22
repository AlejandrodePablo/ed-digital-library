package com.iesam.digitallibrary.features.loan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ListReturnedLoansUseCaseTest {

    @Mock
    LoanRepository loanRepository;

    ListReturnedLoansUseCase listReturnedLoansUseCase;
    @BeforeEach
    void setUp() {
        listReturnedLoansUseCase = new ListReturnedLoansUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        listReturnedLoansUseCase = null;
    }

    @Test
    public void ifExistsReturnedLoansThenReturnTheLoanList() {
        // Given
        Loan returnedLoan = new Loan("1", null, null, "2023-05-10", "2023-05-20");
        Loan unreturnedLoan = new Loan("2", null, null, "2024-06-10", "2024-06-20");

        Mockito.when(loanRepository.getLoans()).thenReturn(List.of(returnedLoan));

        // When
        List<Loan> loans = listReturnedLoansUseCase.execute();

        // Then
        Assertions.assertFalse(loans.isEmpty());
        Assertions.assertEquals(1, loans.size());
        Assertions.assertTrue(loans.contains(returnedLoan));
        Assertions.assertFalse(loans.contains(unreturnedLoan));
    }
    @Test
    public void ifNoReturnedLoansThenReturnAnEmptyList() {
        // Given
        Mockito.when(loanRepository.getLoans()).thenReturn(Collections.emptyList());

        // When
        List<Loan> loans = listReturnedLoansUseCase.execute();

        // Then
        Assertions.assertTrue(loans.isEmpty());
    }
    @Test
    public void ifParseExceptionOccursThenHandleGracefully() {
        // Given
        Loan invalidLoan = new Loan("3", null, null, "invalid-date", "invalid-date");

        Mockito.when(loanRepository.getLoans()).thenReturn(List.of(invalidLoan));

        // When
        List<Loan> loans = listReturnedLoansUseCase.execute();

        // Then
        Assertions.assertTrue(loans.isEmpty());
    }
}