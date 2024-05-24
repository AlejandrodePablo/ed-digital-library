package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ListAudiobooksUseCaseTest {
    @Mock
    AudiobookRepository audiobookRepository;

    ListAudiobooksUseCase listAudiobooksUseCase;

    @BeforeEach
    void setUp() {
        listAudiobooksUseCase = new ListAudiobooksUseCase(audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        listAudiobooksUseCase = null;
    }

    @Test
    public void ifExistsAudiobooksThenReturnTheAudiobookList() {
        // Given
        Audiobook audiobook1 = new Audiobook("100", "Title1", "Author1", "Comedy", "2024", "60");
        Audiobook audiobook2 = new Audiobook("200", "Title2", "Author2", "Fiction", "2024", "120");
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(List.of(audiobook1, audiobook2));

        // When
        List<Audiobook> audiobooks = listAudiobooksUseCase.execute();

        // Then
        Assertions.assertFalse(audiobooks.isEmpty());
        Assertions.assertEquals(2, audiobooks.size());
        Assertions.assertTrue(audiobooks.contains(audiobook1));
        Assertions.assertTrue(audiobooks.contains(audiobook2));
    }

    @Test
    public void ifTheAudiobookListIsEmptyThenReturnAnEmptyList() {
        // Given
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(Collections.emptyList());

        // When
        List<Audiobook> audiobookList = listAudiobooksUseCase.execute();

        // Then
        Assertions.assertTrue(audiobookList.isEmpty());
    }

    @Test
    public void ifExistsSingleAudiobookThenReturnTheSingleAudiobookList() {
        // Given
        Audiobook audiobook = new Audiobook("100", "Title1", "Author1", "Comedy", "2024", "167");
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(List.of(audiobook));

        // When
        List<Audiobook> audiobooks = listAudiobooksUseCase.execute();

        // Then
        Assertions.assertFalse(audiobooks.isEmpty());
        Assertions.assertEquals(1, audiobooks.size());
        Assertions.assertTrue(audiobooks.contains(audiobook));
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobooks();
    }

}