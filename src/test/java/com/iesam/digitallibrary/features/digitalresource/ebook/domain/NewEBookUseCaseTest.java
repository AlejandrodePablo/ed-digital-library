package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.NewEBookUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NewEBookUseCaseTest {

    @Mock
    EBookRepository eBookRepository;

    NewEBookUseCase newEBookUseCase;

    @BeforeEach
    void setUp() {
        newEBookUseCase = new NewEBookUseCase(eBookRepository);
    }

    @AfterEach
    void tearDown() {
        newEBookUseCase = null;
    }

    @Test
    public void receiveAnEBookAndSaveIt(){
        //Given
        EBook eBookToSave = new EBook("Title", "Author", "Comedy", "2024", "English");

        //When
        newEBookUseCase.execute(eBookToSave);

        //Then
        Mockito.verify(eBookRepository, Mockito.times(1)).createEBook(eBookToSave);
    }

    @Test
    public void ifEBookIsNullThenNeverCreateNewEBook(){
        //Given
        EBook nullEBook = null;

        //When
        newEBookUseCase.execute(nullEBook);

        //Then
        Mockito.verify(eBookRepository, Mockito.never()).createEBook(Mockito.any(EBook.class));
    }
}