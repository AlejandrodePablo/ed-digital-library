package com.iesam.digitallibrary.features.loan.data;

import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.Loan;
import com.iesam.digitallibrary.features.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class LoanDataRepositoryTest {

    @Mock
    private LoanFileLocalDataSource loanFileLocalDataSource;

    private LoanDataRepository loanDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loanDataRepository = new LoanDataRepository(loanFileLocalDataSource);
    }

    @AfterEach
    void tearDown() {
        reset(loanFileLocalDataSource);
    }

    @Test
    void createLoanSuccessfully() {
        // Given
        User user = new User("100","dni", "nombre", "ape", "email", "age", "tel");
        DigitalResource digitalResource = new DigitalResource("isbn","title", "author", "genre", "year");

        Loan loan = new Loan("1", user, digitalResource, "2023-05-01", "2023-05-15");

        // When
        loanDataRepository.createLoan(loan);

        // Then
        Mockito.verify(loanFileLocalDataSource, Mockito.times(1)).save(loan);
    }


    @Test
    void getLoansSuccessfully() {
        // Given

        User user = new User("100","dni", "nombre", "ape", "email", "age", "tel");
        DigitalResource digitalResource = new DigitalResource("isbn","title", "author", "genre", "year");
        List<Loan> expectedLoans = Arrays.asList(
                new Loan("1", user, digitalResource, "2023-05-01", "2023-05-15"),
                new Loan("2", user, digitalResource, "2023-05-02", "2023-05-16")
        );
        Mockito.when(loanFileLocalDataSource.findAll()).thenReturn(expectedLoans);

        // When
        List<Loan> loans = loanDataRepository.getLoans();

        // Then
        Assertions.assertEquals(expectedLoans, loans);
    }

    @Test
    void deleteLoanSuccessfully() {
        // Given
        String loanId = "1";

        // When
        loanDataRepository.deleteLoan(loanId);

        // Then
        Mockito.verify(loanFileLocalDataSource, Mockito.times(1)).delete(loanId);
    }
}
