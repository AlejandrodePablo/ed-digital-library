package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class NewAudiobookUseCase {

    private AudiobookRepository audiobookRepository;

    public NewAudiobookUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    public void execute(Audiobook audiobook){
        this.audiobookRepository.createAudiobook(audiobook);
    }
}
