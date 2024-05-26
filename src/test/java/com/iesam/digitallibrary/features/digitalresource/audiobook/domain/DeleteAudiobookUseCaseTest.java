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
class DeleteAudiobookUseCaseTest {

    @Mock
    AudiobookRepository audiobookRepository;

    DeleteAudiobookUseCase deleteAudiobookUseCase;

    @BeforeEach
    void setUp() {
        deleteAudiobookUseCase = new DeleteAudiobookUseCase(audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        deleteAudiobookUseCase = null;
    }
    @Test
    void givenExistingAudiobookThenDeleteAudiobook() {
        // Given
        String isbn = "123";
        Audiobook existingAudiobook = new Audiobook("Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook(isbn)).thenReturn(existingAudiobook);

        // When
        deleteAudiobookUseCase.execute(isbn);

        // Then
        Mockito.verify(audiobookRepository, Mockito.times(1)).deleteAudiobook(isbn);
    }

    @Test
    void givenNonExistingAudiobookThenThrowException() {
        // Given
        String nonExistingIsbn = "456";
        Mockito.when(audiobookRepository.getAudiobook(nonExistingIsbn)).thenReturn(null);

        // When
        DeleteAudiobookUseCase deleteAudiobookUseCase = new DeleteAudiobookUseCase(audiobookRepository);

        // Then
        try {
            deleteAudiobookUseCase.execute(nonExistingIsbn);
            Assertions.fail("Expected AudiobookNotFoundException when deleting non-existing audiobook");
        } catch (AudiobookNotFoundException e) {
            // Expected exception
        }

        // Verifying that the repository's delete method was not called
        Mockito.verify(audiobookRepository, Mockito.never()).deleteAudiobook(Mockito.anyString());
    }
}