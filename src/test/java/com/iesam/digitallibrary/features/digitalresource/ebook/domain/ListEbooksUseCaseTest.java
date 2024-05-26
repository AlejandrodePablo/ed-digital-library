package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.ListEbooksUseCase;
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

@ExtendWith(MockitoExtension.class)
class ListEbooksUseCaseTest {

    @Mock
    EBookRepository eBookRepository;

    ListEbooksUseCase listEbooksUseCase;

    @BeforeEach
    void setUp() {
        listEbooksUseCase = new ListEbooksUseCase(eBookRepository);
    }

    @AfterEach
    void tearDown() {
        listEbooksUseCase = null;
    }

    @Test
    public void ifExistsEBooksThenReturnTheEBookList(){
        //Given
        EBook eBook1 = new EBook("Title1", "Author1", "Comedy", "2024","Spanish");
        EBook eBook2 = new EBook("Title2", "Author2", "Fiction", "2024","Spanish");
        Mockito.when(eBookRepository.getEBooks()).thenReturn(List.of(eBook1, eBook2));

        //When
        List<EBook> eBooks = listEbooksUseCase.execute();

        //Then
        Assertions.assertFalse(eBooks.isEmpty());
        Assertions.assertEquals(2, eBooks.size());
        Assertions.assertTrue(eBooks.contains(eBook1));
        Assertions.assertTrue(eBooks.contains(eBook2));
    }

    @Test
    public void ifTheEBookListIsEmptyThenReturnAnEmptyList(){
        //Given
        Mockito.when(eBookRepository.getEBooks()).thenReturn(Collections.emptyList());

        //When
        List<EBook> eBookList = listEbooksUseCase.execute();

        //Then
        Assertions.assertTrue(eBookList.isEmpty());
    }

    @Test
    public void ifExistsSingleEBookThenReturnTheSingleEBookList() {
        // Given
        EBook eBook = new EBook("Title1", "Author1", "Comedy", "2024", "Spanish");
        Mockito.when(eBookRepository.getEBooks()).thenReturn(List.of(eBook));

        // When
        List<EBook> eBooks = listEbooksUseCase.execute();

        // Then
        Assertions.assertFalse(eBooks.isEmpty());
        Assertions.assertEquals(1, eBooks.size());
        Assertions.assertTrue(eBooks.contains(eBook));
        Mockito.verify(eBookRepository,Mockito.times(1)).getEBooks();
    }
}