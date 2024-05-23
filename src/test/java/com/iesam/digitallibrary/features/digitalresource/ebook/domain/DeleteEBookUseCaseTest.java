package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.DeleteEBookUseCase;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteEBookUseCaseTest {
    @Mock
    EBookRepository eBookRepository;

    DeleteEBookUseCase deleteEBookUseCase;

    @BeforeEach
    void setUp() {
        deleteEBookUseCase = new DeleteEBookUseCase(eBookRepository);
    }

    @AfterEach
    void tearDown() {
        deleteEBookUseCase = null;
    }

    @Test
    public void deletingAnExistingEBookByIsbn(){
        //Given
        String existingIsbn = "100";
        Mockito.doNothing().when(eBookRepository).deleteEBook(existingIsbn);

        // When
        deleteEBookUseCase.execute(existingIsbn);

        // Then
        Mockito.verify(eBookRepository, Mockito.times(1)).deleteEBook(existingIsbn);
    }

    @Test
    public void givingAnInvalidIsbnThenDoesNotDeleteAnyEBook(){
        //Given
        String invalidIsbn = "";
        Mockito.doNothing().when(eBookRepository).deleteEBook(invalidIsbn);

        //When
        deleteEBookUseCase.execute(invalidIsbn);

        //Then
        Mockito.verify(eBookRepository, Mockito.times(1)).deleteEBook(invalidIsbn);
    }

    @Test
    public void givingNullIsbnThenNeverDeleteAnyEBook() {
        // Given
        String nullIsbn = null;

        // When
        deleteEBookUseCase.execute(nullIsbn);

        // Then
        Mockito.verify(eBookRepository, Mockito.never()).deleteEBook(Mockito.anyString());
    }

}