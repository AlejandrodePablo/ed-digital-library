package com.iesam.digitallibrary.features.digitalresource.audiobook.data;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;

import java.util.List;

public class AudiobookDataRepository implements AudiobookRepository {

    private final AudiobookFileLocalDataSource audiobookFileLocalDataSource;

    public AudiobookDataRepository(AudiobookFileLocalDataSource audiobookFileLocalDataSource) {
        this.audiobookFileLocalDataSource = audiobookFileLocalDataSource;
    }

    @Override
    public void createAudiobook(Audiobook audiobook) {
        audiobookFileLocalDataSource.save(audiobook);
    }

    @Override
    public List<Audiobook> getAudiobooks() {
        return audiobookFileLocalDataSource.findAll();
    }

    @Override
    public Audiobook getAudiobook(String isbn) {
        return audiobookFileLocalDataSource.findById(isbn);
    }

    @Override
    public void deleteAudiobook(String isbn) {
        audiobookFileLocalDataSource.delete(isbn);
    }

    @Override
    public void updateAudiobook(Audiobook audiobook) {
        audiobookFileLocalDataSource.update(audiobook);
    }
}
