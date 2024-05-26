package com.iesam.digitallibrary.features.digitalresource.ebook.data;

import com.iesam.digitallibrary.features.digitalresource.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EBookDataRepositoryTest {

    @Mock
    private EBookFileLocalDataSource eBookFileLocalDataSource;

    private EBookDataRepository eBookDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        eBookDataRepository = new EBookDataRepository(eBookFileLocalDataSource);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(eBookFileLocalDataSource);
    }

    @Test
    void createEBookSuccessfully() {
        // Given
        EBook eBook = new EBook("isbn1", "title1", "author1", "genre1", "year1");

        // When
        eBookDataRepository.createEBook(eBook);

        // Then
        Mockito.verify(eBookFileLocalDataSource, Mockito.times(1)).save(eBook);
    }

    @Test
    void deleteEBookSuccessfully() {
        // Given
        String isbn = "isbn1";

        // When
        eBookDataRepository.deleteEBook(isbn);

        // Then
        Mockito.verify(eBookFileLocalDataSource, Mockito.times(1)).delete(isbn);
    }

    @Test
    void getEBookSuccessfully() {
        // Given
        String isbn = "isbn1";
        EBook expectedEBook = new EBook(isbn, "title1", "author1", "genre1", "year1");
        Mockito.when(eBookFileLocalDataSource.findById(isbn)).thenReturn(expectedEBook);

        // When
        EBook eBook = eBookDataRepository.getEBook(isbn);

        // Then
        Mockito.verify(eBookFileLocalDataSource, Mockito.times(1)).findById(isbn);
        assertEquals(expectedEBook, eBook);
    }

    @Test
    void getEBooksSuccessfully() {
        // Given
        EBook eBook1 = new EBook("isbn1", "title1", "author1", "genre1", "year1");
        EBook eBook2 = new EBook("isbn2", "title2", "author2", "genre2", "year2");
        List<EBook> expectedEBooks = Arrays.asList(eBook1, eBook2);
        when(eBookFileLocalDataSource.findAll()).thenReturn(expectedEBooks);

        // When
        List<EBook> eBooks = eBookDataRepository.getEBooks();

        // Then
        Mockito.verify(eBookFileLocalDataSource, Mockito.times(1)).findAll();
        assertEquals(expectedEBooks, eBooks);
    }

    @Test
    void updateEBookSuccessfully() {
        // Given
        EBook eBook = new EBook("isbn1", "title1", "author1", "genre1", "year1");

        // When
        eBookDataRepository.updateEBook(eBook);

        // Then
        Mockito.verify(eBookFileLocalDataSource, Mockito.times(1)).update(eBook);
    }
}

