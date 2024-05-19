package com.iesam.digitallibrary.features.ebook.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateEBookUseCaseTest {

    @Mock
    EBookRepository eBookRepository;

    UpdateEBookUseCase updateEBookUseCase;

    @BeforeEach
    void setUp() {
        updateEBookUseCase = new UpdateEBookUseCase(eBookRepository);
    }

    @AfterEach
    void tearDown() {
        updateEBookUseCase = null;
    }

    @Test
    public void ifIsbnExistThenUpdateUser() {
        //Given
        EBook updatedEbook = new EBook("100", "Title", "Author", "Romance", "2024", "French");
        Mockito.doNothing().when(eBookRepository).updateEBook(Mockito.any(EBook.class));

        //When
        updateEBookUseCase.execute(updatedEbook);

        //Then
        Mockito.verify(eBookRepository, Mockito.times(1)).updateEBook(Mockito.any(EBook.class));
    }

    @Test
    public void ifEBookIsNullThenNoUpdateEBook() {
        //Given
        EBook nullEBook = null;

        //When
        updateEBookUseCase.execute(nullEBook);

        //Then
        Mockito.verify(eBookRepository, Mockito.never()).updateEBook(Mockito.any(EBook.class));
    }

}