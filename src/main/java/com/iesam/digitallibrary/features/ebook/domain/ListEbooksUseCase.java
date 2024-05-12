package com.iesam.digitallibrary.features.ebook.domain;

import java.util.List;

public class ListEbooksUseCase {
    private EBookRepository eBookRepository;

    public ListEbooksUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public List<EBook> execute(){
        return eBookRepository.getEBooks();
    }
}
