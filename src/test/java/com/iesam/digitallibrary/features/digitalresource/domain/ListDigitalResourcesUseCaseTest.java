package com.iesam.digitallibrary.features.digitalresource.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ListDigitalResourcesUseCaseTest {
    @Mock
    DigitalResourceRepository digitalResourceRepository;
    @Mock
    EBookRepository eBookRepository;
    @Mock
    AudiobookRepository audiobookRepository;
    ListDigitalResourcesUseCase listDigitalResourcesUseCase;

    @BeforeEach
    void setUp() {
        listDigitalResourcesUseCase = new ListDigitalResourcesUseCase(digitalResourceRepository, eBookRepository, audiobookRepository);
    }

    @AfterEach
    void tearDown() {
        listDigitalResourcesUseCase = null;
    }

    @Test
    public void givenEBooksAndAudiobooksThenReturnCombinedList() {
        // Given
        EBook eBook1 = new EBook("EBook Title 1", "Author 1", "Genre 1", "2021", "English");
        EBook eBook2 = new EBook("EBook Title 2", "Author 2", "Genre 2", "2022", "Spanish");
        List<EBook> eBooks = Arrays.asList(eBook1, eBook2);

        Audiobook audiobook1 = new Audiobook("Audiobook Title 1", "Author 3", "Genre 3", "2023", "120");
        Audiobook audiobook2 = new Audiobook( "Audiobook Title 2", "Author 4", "Genre 4", "2024", "150");
        List<Audiobook> audiobooks = Arrays.asList(audiobook1, audiobook2);

        Mockito.when(eBookRepository.getEBooks()).thenReturn(eBooks);
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(audiobooks);

        // When
        List<DigitalResource> digitalResources = listDigitalResourcesUseCase.execute();

        // Then
        Assertions.assertEquals(4, digitalResources.size());
        Assertions.assertEquals(eBooks.get(0), digitalResources.get(0));
        Assertions.assertEquals(eBooks.get(1), digitalResources.get(1));
        Assertions.assertEquals(audiobooks.get(0), digitalResources.get(2));
        Assertions.assertEquals(audiobooks.get(1), digitalResources.get(3));

        Mockito.verify(eBookRepository, Mockito.times(1)).getEBooks();
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobooks();
    }

    @Test
    public void givenNoEBooksOrAudiobooksThenReturnEmptyList() {
        // Given
        Mockito.when(eBookRepository.getEBooks()).thenReturn(Arrays.asList());
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(Arrays.asList());

        // When
        List<DigitalResource> digitalResources = listDigitalResourcesUseCase.execute();

        // Then
        Assertions.assertEquals(0, digitalResources.size());

        Mockito.verify(eBookRepository, Mockito.times(1)).getEBooks();
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobooks();
    }

    @Test
    void givenSomeEmptyRepositoriesThenReturnNonEmptyList() {
        // Given
        EBook eBook1 = new EBook("EBook Title 1", "Author 1", "Genre 1", "2021", "English");
        List<EBook> eBooks = Arrays.asList(eBook1);

        List<Audiobook> audiobooks = Arrays.asList();  // No audiobooks

        Mockito.when(eBookRepository.getEBooks()).thenReturn(eBooks);
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(audiobooks);

        // When
        List<DigitalResource> digitalResources = listDigitalResourcesUseCase.execute();

        // Then
        Assertions.assertEquals(1, digitalResources.size());
        Assertions.assertEquals(eBooks.get(0), digitalResources.get(0));

        Mockito.verify(eBookRepository, Mockito.times(1)).getEBooks();
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobooks();
    }

    @Test
    void givenOnlyAudiobooksThenReturnNonEmptyList() {
        // Given
        List<EBook> eBooks = Arrays.asList();  // No eBooks

        Audiobook audiobook1 = new Audiobook("Audiobook Title 1", "Author 3", "Genre 3", "2023", "120");
        Audiobook audiobook2 = new Audiobook("Audiobook Title 2", "Author 4", "Genre 4", "2024", "150");
        List<Audiobook> audiobooks = Arrays.asList(audiobook1, audiobook2);

        Mockito.when(eBookRepository.getEBooks()).thenReturn(eBooks);
        Mockito.when(audiobookRepository.getAudiobooks()).thenReturn(audiobooks);

        // When
        List<DigitalResource> digitalResources = listDigitalResourcesUseCase.execute();

        // Then
        Assertions.assertEquals(2, digitalResources.size());
        Assertions.assertEquals(audiobooks.get(0), digitalResources.get(0));
        Assertions.assertEquals(audiobooks.get(1), digitalResources.get(1));

        Mockito.verify(eBookRepository, Mockito.times(1)).getEBooks();
        Mockito.verify(audiobookRepository, Mockito.times(1)).getAudiobooks();
    }

}
