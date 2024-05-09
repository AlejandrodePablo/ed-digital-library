package com.iesam.digitallibrary.features.ebook.domain;

public class DeleteEBookUseCase {

    private EBookRepository eBookRepository;

    public DeleteEBookUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public void execute(String isbn) {
        this.eBookRepository.deleteEBook(isbn);
    }
}
