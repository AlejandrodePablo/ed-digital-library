package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class UpdateAudiobookUseCase {

    private AudiobookRepository audiobookRepository;

    public UpdateAudiobookUseCase(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    public void execute(String isbn, Audiobook updatedAudiobook){
        Audiobook existingAudiobook = this.audiobookRepository.getAudiobook(isbn);
        if (existingAudiobook == null) {
            throw new AudiobookNotFoundException("Audiobook with ISBN " + isbn + " does not exist");
        }else{
            this.audiobookRepository.updateAudiobook(updatedAudiobook);
        }
    }
}
