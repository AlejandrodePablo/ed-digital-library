package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class GetAudiobookUseCase {

    private AudiobookRepository audiobookRepository;

    public GetAudiobookUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    public Audiobook execute(String isbn) {
        return this.audiobookRepository.getAudiobook(isbn);
    }
}
