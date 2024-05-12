package com.iesam.digitallibrary.features.ebook.domain;

public class UpdateEBookUseCase {
    private EBookRepository eBookRepository;

    public UpdateEBookUseCase(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    public void execute(EBook eBook) {
        eBookRepository.updateEBook(eBook);
    }

}
