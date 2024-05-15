package com.iesam.digitallibrary.features.ebook.domain;

public class GetEBookUseCase {

    private EBookRepository eBookRepository;

    public GetEBookUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public EBook execute(String isbn) {
        return this.eBookRepository.getEBook(isbn);
    }
}
