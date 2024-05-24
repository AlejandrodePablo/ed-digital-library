package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class GetAudiobookUseCase {

    private AudiobookRepository audiobookRepository;

    public GetAudiobookUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    public Audiobook execute(String isbn) {
        Audiobook audiobook = this.audiobookRepository.getAudiobook(isbn);
        if (audiobook == null) {
            throw new AudiobookNotFoundException("Audiobook with ISBN " + isbn + " does not exist");
        }
        return audiobook;
    }
}
