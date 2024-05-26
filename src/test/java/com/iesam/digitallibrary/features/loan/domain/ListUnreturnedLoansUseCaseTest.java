package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ListUnreturnedLoansUseCaseTest {

    @Mock
    LoanRepository loanRepository;

    ListUnreturnedLoansUseCase listUnreturnedLoansUseCase;
    @BeforeEach
    void setUp() {
        listUnreturnedLoansUseCase = new ListUnreturnedLoansUseCase(loanRepository);
    }

    @AfterEach
    void tearDown() {
        listUnreturnedLoansUseCase = null;
    }

    @Test
    public void givenUnreturnedLoansThenReturnListOfUnreturnedLoans() {
        // Given
        List<Loan> unreturnedLoans = new ArrayList<>();
        Loan unreturnedLoan1 = new Loan("123",
                new User(null,null,null,null,null,null),
                new EBook(null,null,null,null,null),
                "2024-05-25", "2024-06-05");

        Loan unreturnedLoan2 = new Loan("456",
                new User(null,null,null,null,null,null,null),
                new EBook(null,null,null,null,null),
                "2024-05-26", "2024-06-06");

        unreturnedLoans.add(unreturnedLoan1);
        unreturnedLoans.add(unreturnedLoan2);
        Mockito.when(loanRepository.getLoans()).thenReturn(unreturnedLoans);

        // When
        List<Loan> result = listUnreturnedLoansUseCase.execute();

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).getLoans();
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(unreturnedLoan1));
        Assertions.assertTrue(result.contains(unreturnedLoan2));
    }
    @Test
    public void ifParseExceptionOccursThenHandleGracefully() {
        // Given
        Loan invalidLoan = new Loan("3", null, null, "invalid-date", "invalid-date");

        Mockito.when(loanRepository.getLoans()).thenReturn(List.of(invalidLoan));

        // When
        List<Loan> loans = listUnreturnedLoansUseCase.execute();

        // Then
        Assertions.assertTrue(loans.isEmpty());
    }

    @Test
    public void ifNoUnreturnedReturnedLoansThenReturnAnEmptyList() {
        // Given
        Mockito.when(loanRepository.getLoans()).thenReturn(Collections.emptyList());

        // When
        List<Loan> loans = listUnreturnedLoansUseCase.execute();

        // Then
        Assertions.assertTrue(loans.isEmpty());
    }
}