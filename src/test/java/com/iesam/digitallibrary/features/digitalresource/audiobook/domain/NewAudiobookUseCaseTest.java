package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewAudiobookUseCaseTest {

    @Mock
    AudiobookRepository audiobookRepository;

    NewAudiobookUseCase newAudiobookUseCase;

    @BeforeEach
    void setUp() {
        newAudiobookUseCase = new NewAudiobookUseCase(audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        newAudiobookUseCase = null;
    }

    @Test
    void givenValidAudiobookThenSaveAudiobook() {
        // Given
        Audiobook validAudiobook = new Audiobook( "Title", "Author", "Genre", "Year", "Duration");

        // When
        newAudiobookUseCase.execute(validAudiobook);

        // Then
        Mockito.verify(audiobookRepository, Mockito.times(1)).createAudiobook(validAudiobook);
    }

    @Test
    void givenInvalidAudiobookThenDoNotSaveAudiobook() {
        // Given
        Audiobook invalidAudiobook = null;

        // When
        newAudiobookUseCase.execute(invalidAudiobook);

        // Then
        Mockito.verify(audiobookRepository, Mockito.never()).createAudiobook(Mockito.any(Audiobook.class));
    }

}