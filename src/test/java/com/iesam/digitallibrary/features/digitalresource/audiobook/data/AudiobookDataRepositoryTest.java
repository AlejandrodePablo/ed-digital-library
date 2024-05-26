package com.iesam.digitallibrary.features.digitalresource.audiobook.data;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
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

public class AudiobookDataRepositoryTest {

    @Mock
    private AudiobookFileLocalDataSource audiobookFileLocalDataSource;

    private AudiobookDataRepository audiobookDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        audiobookDataRepository = new AudiobookDataRepository(audiobookFileLocalDataSource);
    }

    @AfterEach
    void tearDown() {
        reset(audiobookFileLocalDataSource);
    }

    @Test
    void createAudiobookSuccessfully() {
        // Given
        Audiobook audiobook = new Audiobook("isbn1", "title1", "author1", "narrator1", "duration1");

        // When
        audiobookDataRepository.createAudiobook(audiobook);

        // Then
        Mockito.verify(audiobookFileLocalDataSource, Mockito.times(1)).save(audiobook);
    }

    @Test
    void getAudiobooksSuccessfully() {
        // Given
        Audiobook audiobook1 = new Audiobook("isbn1", "title1", "author1", "narrator1", "duration1");
        Audiobook audiobook2 = new Audiobook("isbn2", "title2", "author2", "narrator2", "duration2");
        List<Audiobook> expectedAudiobooks = Arrays.asList(audiobook1, audiobook2);
        Mockito.when(audiobookFileLocalDataSource.findAll()).thenReturn(expectedAudiobooks);

        // When
        List<Audiobook> audiobooks = audiobookDataRepository.getAudiobooks();

        // Then
        Mockito.verify(audiobookFileLocalDataSource, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedAudiobooks, audiobooks);
    }

    @Test
    void getAudiobookSuccessfully() {
        // Given
        String isbn = "isbn1";
        Audiobook expectedAudiobook = new Audiobook(isbn, "title1", "author1", "narrator1", "duration1");
        Mockito.when(audiobookFileLocalDataSource.findById(isbn)).thenReturn(expectedAudiobook);

        // When
        Audiobook audiobook = audiobookDataRepository.getAudiobook(isbn);

        // Then
        Mockito.verify(audiobookFileLocalDataSource, Mockito.times(1)).findById(isbn);
        Assertions.assertEquals(expectedAudiobook, audiobook);
    }

    @Test
    void deleteAudiobookSuccessfully() {
        // Given
        String isbn = "isbn1";

        // When
        audiobookDataRepository.deleteAudiobook(isbn);

        // Then
        Mockito.verify(audiobookFileLocalDataSource, Mockito.times(1)).delete(isbn);
    }

    @Test
    void updateAudiobookSuccessfully() {
        // Given
        Audiobook audiobook = new Audiobook("isbn1", "title1", "author1", "narrator1", "duration1");

        // When
        audiobookDataRepository.updateAudiobook(audiobook);

        // Then
        Mockito.verify(audiobookFileLocalDataSource, Mockito.times(1)).update(audiobook);
    }
}
