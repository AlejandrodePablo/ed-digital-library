package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

public class AudiobookNotFoundException extends RuntimeException {
    public AudiobookNotFoundException(String message) {
        super(message);
    }
}
