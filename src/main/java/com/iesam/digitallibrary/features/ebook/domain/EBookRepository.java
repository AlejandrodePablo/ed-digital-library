package com.iesam.digitallibrary.features.ebook.domain;

public interface EBookRepository {

    void createEBook(EBook eBook);

    void deleteEBook(String isbn);
}
