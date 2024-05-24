package com.iesam.digitallibrary.features.digitalresource.audiobook.data;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;

public class AudiobookDataRepository implements AudiobookRepository {

    private final AudiobookFileLocalDataSource audiobookFileLocalDataSource;

    public AudiobookDataRepository(AudiobookFileLocalDataSource audiobookFileLocalDataSource) {
        this.audiobookFileLocalDataSource = audiobookFileLocalDataSource;
    }

    @Override
    public void createAudiobook(Audiobook audiobook) {
        audiobookFileLocalDataSource.save(audiobook);
    }
}
