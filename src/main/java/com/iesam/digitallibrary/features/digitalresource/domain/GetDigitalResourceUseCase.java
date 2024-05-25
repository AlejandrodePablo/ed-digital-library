package com.iesam.digitallibrary.features.digitalresource.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.digitalresource.presentation.DigitalResourcePresentation;

public class GetDigitalResourceUseCase {

    private final DigitalResourceRepository digitalResourceRepository;
    private final EBookRepository eBookRepository;
    private final AudiobookRepository audiobookRepository;

    public GetDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository, EBookRepository eBookRepository, AudiobookRepository audiobookRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
        this.eBookRepository = eBookRepository;
        this.audiobookRepository = audiobookRepository;
    }

    public DigitalResource getDigitalResource(String isbn) {
        EBook eBook = eBookRepository.getEBook(isbn);
        if (eBook != null) {
            return eBook;
        }

        Audiobook audiobook = audiobookRepository.getAudiobook(isbn);
        if (audiobook != null) {
            return audiobook;
        }

        return null;
    }
}
