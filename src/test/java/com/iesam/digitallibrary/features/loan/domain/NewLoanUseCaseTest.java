package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class NewLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    EBookRepository eBookRepository;
    @Mock
    AudiobookRepository audiobookRepository;

    NewLoanUseCase newLoanUseCase;

    @BeforeEach
    void setUp() {
        newLoanUseCase = new NewLoanUseCase(loanRepository, userRepository, eBookRepository, audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        newLoanUseCase = null;
    }

    @Test
    void givenValidEBookThenSaveLoan() {
        // Given
        String loanId = "123";
        String userId = "456";
        String isbn = "789";
        String startDate = "2024-05-01";
        String returnDate = "2024-05-15";

        User user = new User(userId, "Dni", "Nombre", "Apellido", "email@example.com", "23","000000000");
        EBook eBook = new EBook("Title", "Author", "Genre", "2024", "Language");

        Mockito.when(userRepository.getUser(userId)).thenReturn(user);
        Mockito.when(eBookRepository.getEBook(isbn)).thenReturn(eBook);

        // When
        boolean result = newLoanUseCase.execute(userId, isbn, startDate, returnDate);

        // Then
        Assertions.assertTrue(result);
        Mockito.verify(loanRepository).createLoan(Mockito.any(Loan.class));
    }

    @Test
    void givenValidAudiobookThenSaveLoan() {
        // Given
        String loanId = "123";
        String userId = "456";
        String isbn = "789";
        String startDate = "2024-05-01";
        String returnDate = "2024-05-15";

        User user = new User(userId, "Dni", "Nombre", "Apellido", "email@example.com", "23","000000000");
        Audiobook audiobook = new Audiobook("Title", "Author", "Genre", "2024", "duration");

        Mockito.when(userRepository.getUser(userId)).thenReturn(user);
        Mockito.when(audiobookRepository.getAudiobook(isbn)).thenReturn(audiobook);

        // When
        boolean result = newLoanUseCase.execute(userId, isbn, startDate, returnDate);

        // Then
        Assertions.assertTrue(result);
        Mockito.verify(loanRepository).createLoan(Mockito.any(Loan.class));
    }

}