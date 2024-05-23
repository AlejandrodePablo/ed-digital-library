package com.iesam.digitallibrary.features.ebook.domain;

public class EBook {

    public final String isbn;
    public final String title;
    public final String author;
    public final String genre;
    public final String publicationYear;
    public final String language;

    public EBook(String isbn, String title, String author, String genre, String publicationYear, String language) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.language = language;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
