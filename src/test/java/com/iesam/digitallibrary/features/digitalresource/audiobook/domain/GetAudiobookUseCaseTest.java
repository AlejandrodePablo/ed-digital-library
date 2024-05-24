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
    public void givingAValidIsbnThenGetAudiobook(){
        //Given
        String validIsbn = "123";
        Audiobook audiobookExpected = new Audiobook(validIsbn, "Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook("123")).thenReturn(audiobookExpected);

        //When
        Audiobook audiobookReceived = getAudiobookUseCase.execute(validIsbn);

        //Then
        Assertions.assertEquals(audiobookReceived.isbn, "123");
        Assertions.assertEquals(audiobookReceived.title, "Title");
        Assertions.assertEquals(audiobookReceived.author, "Author");
        Assertions.assertEquals(audiobookReceived.genre, "Genre");
        Assertions.assertEquals(audiobookReceived.publicationYear, "Year");
        Assertions.assertEquals(audiobookReceived.duration, "Duration");
    }

    @Test
    public void givingANonValidIsbnThenReturnNull(){
        //Given
        Mockito.when(audiobookRepository.getAudiobook("")).thenReturn(null);

        //When
        Audiobook audiobookReceived = getAudiobookUseCase.execute("");

        //Then
        Assertions.assertNull(audiobookReceived);
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook("");

    }

    @Test
    public void testGetAudiobookCallsRepository() {
        // Given
        String audiobookIsbn = "123";
        Audiobook audiobookExpected = new Audiobook(audiobookIsbn, "Title", "Author", "Genre", "Year", "Duration");
        Mockito.when(audiobookRepository.getAudiobook(audiobookIsbn)).thenReturn(audiobookExpected);

        // When
        Audiobook audiobookReceived = getAudiobookUseCase.execute(audiobookIsbn);

        // Then
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(audiobookIsbn);
        Assertions.assertEquals(audiobookExpected, audiobookReceived);
    }

    @Test
    public void givingANullIsbnThenReturnNull(){
        //Given
        Mockito.when(audiobookRepository.getAudiobook(null)).thenReturn(null);

        //When
        Audiobook audiobookReceived = getAudiobookUseCase.execute(null);

        //Then
        Assertions.assertNull(audiobookReceived);
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobook(null);
    }
}