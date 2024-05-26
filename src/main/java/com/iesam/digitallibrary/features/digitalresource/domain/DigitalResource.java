package com.iesam.digitallibrary.features.digitalresource.domain;

public class DigitalResource {
    public final String isbn;
    public final String title;
    public final String author;
    public final String genre;
    public final String publicationYear;

    public DigitalResource(String isbn, String title, String author, String genre, String publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public DigitalResource(String title, String author, String genre, String publicationYear) {
        this.isbn = generateUniqueIsbn();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }
    private static String generateUniqueIsbn() {
        return String.valueOf(System.currentTimeMillis());
    }

}
