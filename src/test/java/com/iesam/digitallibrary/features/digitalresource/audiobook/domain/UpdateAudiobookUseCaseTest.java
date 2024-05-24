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

class UpdateAudiobookUseCaseTest {
    @Mock
    AudiobookRepository audiobookRepository;

    UpdateAudiobookUseCase updateAudiobookUseCase;

    @BeforeEach
    void setUp() {
        updateAudiobookUseCase = new UpdateAudiobookUseCase(audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        updateAudiobookUseCase = null;
    }
    @Test
    public void givenExistingAudiobookThenUpdateAudiobook() {
        // Given
        String existingIsbn = "123";
        Audiobook existingAudiobook = new Audiobook(existingIsbn, "Title", "Author", "Genre", "Year", "Duration");
        Audiobook updatedAudiobook = new Audiobook(existingIsbn, "New Title", "New Author", "New Genre", "New Year", "New Duration");
        Mockito.when(audiobookRepository.getAudiobook(existingIsbn)).thenReturn(existingAudiobook);

        // When
        updateAudiobookUseCase.execute(existingIsbn, updatedAudiobook);

        // Then
        Mockito.verify(audiobookRepository, Mockito.times(1)).updateAudiobook(updatedAudiobook);
    }

    @Test
    void givenNonExistingAudiobookThenThrowException() {
        // Given
        String nonExistingIsbn = "456";
        Audiobook updatedAudiobook = new Audiobook(nonExistingIsbn, "Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook(nonExistingIsbn)).thenReturn(null);

        // When
        try {
            updateAudiobookUseCase.execute(nonExistingIsbn, updatedAudiobook);
            Assertions.fail("Expected AudiobookNotFoundException when updating non-existing audiobook");
        } catch (AudiobookNotFoundException e) {
            // Expected exception
        }

        //Then
        Mockito.verify(audiobookRepository, Mockito.never()).updateAudiobook(Mockito.any(Audiobook.class));
    }
}