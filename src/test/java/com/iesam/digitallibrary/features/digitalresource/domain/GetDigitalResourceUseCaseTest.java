package com.iesam.digitallibrary.features.digitalresource.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDigitalResourceUseCaseTest {

    @Mock
    DigitalResourceRepository digitalResourceRepository;
    @Mock
    EBookRepository eBookRepository;
    @Mock
    AudiobookRepository audiobookRepository;

    GetDigitalResourceUseCase getDigitalResourceUseCase;

    @BeforeEach
    void setUp() {
        getDigitalResourceUseCase = new GetDigitalResourceUseCase(digitalResourceRepository, eBookRepository, audiobookRepository);
    }

    @Test
    void givenExistingEBookThenReturnEBook() {
        // Given
        String isbn = "123456";
        EBook eBook = new EBook(isbn, "Title", "Author", "Genre", "2024", "Language");
        when(eBookRepository.getEBook(isbn)).thenReturn(eBook);

        // When
        DigitalResource result = getDigitalResourceUseCase.getDigitalResource(isbn);

        // Then
        assertEquals(eBook, result);
    }

    @Test
    void givenExistingAudiobookThenReturnAudiobook() {
        // Given
        String isbn = "123456";
        Audiobook audiobook = new Audiobook(isbn, "Title", "Author", "Genre", "2024", "Duration");
        when(audiobookRepository.getAudiobook(isbn)).thenReturn(audiobook);

        // When
        DigitalResource result = getDigitalResourceUseCase.getDigitalResource(isbn);

        // Then
        assertEquals(audiobook, result);
    }

    @Test
    void givenNonExistingDigitalResourceThenReturnNull() {
        // Given
        String isbn = "123456";
        when(eBookRepository.getEBook(isbn)).thenReturn(null);
        when(audiobookRepository.getAudiobook(isbn)).thenReturn(null);

        // When
        DigitalResource result = getDigitalResourceUseCase.getDigitalResource(isbn);

        // Then
        assertEquals(null, result);
    }
}
