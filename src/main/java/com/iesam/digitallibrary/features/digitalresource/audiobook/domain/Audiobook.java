package com.iesam.digitallibrary.features.digitalresource.audiobook.domain;

import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;

public class Audiobook extends DigitalResource {

    public final String duration;

    public Audiobook(String title, String author, String genre, String publicationYear, String duration) {
        super(title, author, genre, publicationYear);
        this.duration = duration;
    }

    public Audiobook(String isbn, String title, String author, String genre, String publicationYear, String duration) {
        super(isbn, title, author, genre, publicationYear); // Usa el ISBN proporcionado enfocado en la prueba de test
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Audiobook{" +
                "Isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
