package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import java.util.List;

public class ListAudiobooksUseCase {

    private AudiobookRepository audiobookRepository;

    public ListAudiobooksUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    public List<Audiobook> execute() {
        return this.audiobookRepository.getAudiobooks();
    }
}
