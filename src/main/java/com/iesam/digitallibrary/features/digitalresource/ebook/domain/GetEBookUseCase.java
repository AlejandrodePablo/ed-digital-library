package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

public class GetEBookUseCase {

    private EBookRepository eBookRepository;

    public GetEBookUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public EBook execute(String isbn) {
        return this.eBookRepository.getEBook(isbn);
    }
}
