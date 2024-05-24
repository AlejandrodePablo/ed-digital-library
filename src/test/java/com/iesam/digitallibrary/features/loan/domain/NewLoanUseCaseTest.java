package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NewLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    EBookRepository eBookRepository;

    NewLoanUseCase newLoanUseCase;

    @BeforeEach
    void setUp() {
        newLoanUseCase = new NewLoanUseCase(loanRepository, userRepository, eBookRepository);
    }

    @AfterEach
    void tearDown() {
        newLoanUseCase = null;
    }

    @Test
    void givenValidLoanThenSaveLoan() {
        // Given
        String loanId = "123";
        String userId = "user123";
        String eBookIsbn = "isbn123";
        String startDate = "2024-05-25";
        String returnDate = "2024-06-05";

        // Mock of UserRepository
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(userId, null, null, null, null, null, null);
        Mockito.when(userRepository.getUser(userId)).thenReturn(user);

        // Mock of EBookRepository
        EBookRepository eBookRepository = Mockito.mock(EBookRepository.class);
        EBook eBook = new EBook(eBookIsbn, null, null, null, null, null);
        Mockito.when(eBookRepository.getEBook(eBookIsbn)).thenReturn(eBook);

        newLoanUseCase = new NewLoanUseCase(loanRepository, userRepository, eBookRepository);

        // When
        boolean success = newLoanUseCase.execute(loanId, userId, eBookIsbn, startDate, returnDate);

        // Then
        Assertions.assertTrue(success);
        Mockito.verify(loanRepository, Mockito.times(1)).createLoan(Mockito.any(Loan.class));
    }

    @Test
    public void ifLoanIsNullThenNeverCreateIt(){
        // Given
        String loanId = null;
        String userId = null;
        String eBookIsbn = null;
        String startDate = null;
        String returnDate = null;

        // When
        boolean success = newLoanUseCase.execute(loanId, userId, eBookIsbn, startDate, returnDate);

        // Then
        Assertions.assertFalse(success);
        Mockito.verify(loanRepository, Mockito.never()).createLoan(Mockito.any(Loan.class));
    }

    @Test
    public void ifUserOrEBookNotFoundThenReturnFalse(){
        // Given
        String loanId = "123";
        String userId = "validUserId"; // valid ID, but user does not exist
        String eBookIsbn = "validEBookIsbn"; // valid ISBN, but eBook does not exist
        String startDate = "2024-05-25";
        String returnDate = "2024-06-05";

        // We simulate that the user repository returns null for the given user ID
        Mockito.when(userRepository.getUser(userId)).thenReturn(null);
        // We simulate that the eBook repository returns null for the given eBook ISBN
        Mockito.when(eBookRepository.getEBook(eBookIsbn)).thenReturn(null);

        // When
        boolean success = newLoanUseCase.execute(loanId, userId, eBookIsbn, startDate, returnDate);

        // Then
        Assertions.assertFalse(success);
        Mockito.verify(loanRepository, Mockito.never()).createLoan(Mockito.any(Loan.class));
    }

}