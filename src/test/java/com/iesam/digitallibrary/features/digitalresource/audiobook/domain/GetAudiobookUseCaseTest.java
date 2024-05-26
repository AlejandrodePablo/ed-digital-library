package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAudiobookUseCaseTest {
    @Mock
    AudiobookRepository audiobookRepository;

    GetAudiobookUseCase getAudiobookUseCase;

    @BeforeEach
    void setUp() {
        getAudiobookUseCase = new GetAudiobookUseCase(audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        getAudiobookUseCase = null;
    }

    @Test
    public void givingAValidIsbnThenGetAudiobook() {
        // Given
        String validIsbn = "123";
        Audiobook audiobookExpected = new Audiobook("123", "Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook(validIsbn)).thenReturn(audiobookExpected);

        // When
        Audiobook audiobookReceived = getAudiobookUseCase.execute(validIsbn);

        // Then
        Assertions.assertEquals(audiobookExpected, audiobookReceived);
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(validIsbn);
    }

    @Test
    public void givingANonValidIsbnThenThrowException() {
        // Given
        String invalidIsbn = "";
        AudiobookNotFoundException exception = new AudiobookNotFoundException(invalidIsbn);
        Mockito.when(audiobookRepository.getAudiobook(invalidIsbn)).thenThrow(exception);

        // When
        try {
            getAudiobookUseCase.execute(invalidIsbn);
            Assertions.fail("Expected AudiobookNotFoundException was not thrown");
        } catch (AudiobookNotFoundException e) {
            // Then
            Assertions.assertEquals(exception.getMessage(), e.getMessage());
            Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(invalidIsbn);
        }
    }

    @Test
    public void givingANullIsbnThenThrowException() {
        // Given
        String nullIsbn = null;
        AudiobookNotFoundException exception = new AudiobookNotFoundException(nullIsbn);
        Mockito.when(audiobookRepository.getAudiobook(nullIsbn)).thenThrow(exception);

        // When
        try {
            getAudiobookUseCase.execute(nullIsbn);
            Assertions.fail("Expected AudiobookNotFoundException was not thrown");
        } catch (AudiobookNotFoundException e) {
            // Then
            Assertions.assertEquals(exception.getMessage(), e.getMessage());
            Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(nullIsbn);
        }
    }

    @Test
    public void testGetAudiobookCallsRepository() {
        // Given
        String audiobookIsbn = "123";
        Audiobook audiobookExpected = new Audiobook("123", "Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook(audiobookIsbn)).thenReturn(audiobookExpected);

        // When
        Audiobook audiobookReceived = getAudiobookUseCase.execute(audiobookIsbn);

        // Then
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(audiobookIsbn);
        Assertions.assertEquals(audiobookExpected, audiobookReceived);
    }
}
