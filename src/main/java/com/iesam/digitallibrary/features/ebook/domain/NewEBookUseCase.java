package com.iesam.digitallibrary.features.ebook.domain;

public class NewEBookUseCase {

    private EBookRepository eBookRepository;

    public NewEBookUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public void execute(EBook eBook) {
        eBookRepository.createEBook(eBook);
    }
}
