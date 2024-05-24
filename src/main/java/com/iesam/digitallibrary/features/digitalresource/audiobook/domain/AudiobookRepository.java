package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import java.util.List;

public interface AudiobookRepository {

    void createAudiobook(Audiobook audiobook);

    List<Audiobook> getAudiobooks();

    Audiobook getAudiobook(String isbn);

    void deleteAudiobook(String isbn);

    void updateAudiobook(Audiobook audiobook);

}
