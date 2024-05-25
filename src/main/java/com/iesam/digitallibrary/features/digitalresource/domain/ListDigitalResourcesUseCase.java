package com.iesam.digitallibrary.features.digitalresource.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;

import java.util.ArrayList;
import java.util.List;

public class ListDigitalResourcesUseCase {

    private DigitalResourceRepository digitalResourceRepository;
    private EBookRepository eBookRepository;
    private AudiobookRepository audiobookRepository;

    public ListDigitalResourcesUseCase(DigitalResourceRepository digitalResourceRepository, EBookRepository eBookRepository, AudiobookRepository audiobookRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
        this.eBookRepository = eBookRepository;
        this.audiobookRepository = audiobookRepository;
    }

    public List<DigitalResource> execute() {
        List<DigitalResource> digitalResources = new ArrayList<>();

        List<EBook> eBooks = eBookRepository.getEBooks();
        List<Audiobook> audiobooks = audiobookRepository.getAudiobooks();

        digitalResources.addAll(eBooks);
        digitalResources.addAll(audiobooks);

        return digitalResources;
    }

}
