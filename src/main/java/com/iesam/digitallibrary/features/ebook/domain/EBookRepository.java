package com.iesam.digitallibrary.features.ebook.domain;

import java.util.List;

public interface EBookRepository {

    void createEBook(EBook eBook);

    void deleteEBook(String isbn);

    EBook getEBook(String isbn);

    List<EBook> getEBooks();
}
