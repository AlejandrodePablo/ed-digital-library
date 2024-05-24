package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class DeleteAudiobookUseCase {

    private AudiobookRepository audiobookRepository;

    public DeleteAudiobookUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }
    public void execute(String isbn){
        Audiobook audiobook = this.audiobookRepository.getAudiobook(isbn);
        if (audiobook == null) {
            throw new AudiobookNotFoundException("Audiobook with ISBN " + isbn + " does not exist");
        }else{
            this.audiobookRepository.deleteAudiobook(isbn);
        }
    }
}
