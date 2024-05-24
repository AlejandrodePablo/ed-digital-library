package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.GetEBookUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetEBookUseCaseTest {
    @Mock
    EBookRepository eBookRepository;

    GetEBookUseCase getEBookUseCase;

    @BeforeEach
    void setUp() {
        getEBookUseCase = new GetEBookUseCase(eBookRepository);
    }

    @AfterEach
    void tearDown() {
        getEBookUseCase = null;
    }

    @Test
    public void givingAValidIdThenReturnAnEBook(){
        //Given
        EBook eBookExpected = new EBook("100", "Title", "Author", "Comedy", "2024", "English");
        Mockito.when(eBookRepository.getEBook("100")).thenReturn(eBookExpected);

        //When
        EBook eBookReceived = getEBookUseCase.execute("100");

        //Then
        Assertions.assertEquals(eBookReceived.isbn, "100");
        Assertions.assertEquals(eBookReceived.title, "Title");
        Assertions.assertEquals(eBookReceived.author, "Author");
        Assertions.assertEquals(eBookReceived.genre, "Comedy");
        Assertions.assertEquals(eBookReceived.publicationYear, "2024");
        Assertions.assertEquals(eBookReceived.language, "English");
    }

    @Test
    public void givingANonValidIsbnThenReturnNull(){
        //Given
        Mockito.when(eBookRepository.getEBook("")).thenReturn(null);

        //When
        EBook eBookReceived = getEBookUseCase.execute("");

        //Then
        Assertions.assertNull(eBookReceived);
        Mockito.verify(eBookRepository, Mockito.times(1)).getEBook("");

    }

    @Test
    public void givingANullIsbnThenReturnNull(){
        //Given
        Mockito.when(eBookRepository.getEBook(null)).thenReturn(null);

        //When
        EBook eBookReceived = getEBookUseCase.execute(null);

        //Then
        Assertions.assertNull(eBookReceived);
        Mockito.verify(eBookRepository, Mockito.times(1)).getEBook(null);
    }
    @Test
    public void testGetEBookCallsRepository() {
        // Given
        String eBookIsbn = "123";
        EBook expectedEBook = new EBook(eBookIsbn, "Title", "Author", "Fiction", "2024", "English");
        Mockito.when(eBookRepository.getEBook(eBookIsbn)).thenReturn(expectedEBook);

        // When
        EBook eBookReceived = getEBookUseCase.execute(eBookIsbn);

        // Then
        Mockito.verify(eBookRepository, Mockito.times(1)).getEBook(eBookIsbn);
        Assertions.assertEquals(expectedEBook, eBookReceived);
    }

}