package com.iesam.digitallibrary.features.digitalresource.domain;

public class DigitalResource {
    public final String id;
    public final String title;
    public final String author;
    public final String genre;
    public final String publicationYear;

    public DigitalResource(String id, String title, String author, String genre, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }
}
