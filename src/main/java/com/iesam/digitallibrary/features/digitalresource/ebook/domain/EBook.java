package com.iesam.digitallibrary.features.digitalresource.ebook.domain;

import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;

public class EBook extends DigitalResource {

    public final String language;

    public EBook(String isbn, String title, String author, String genre, String publicationYear, String language) {
        super(isbn, title, author, genre, publicationYear); // Usa el isbn como id
        this.language = language;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "isbn='" + isbn + '\'' + // Usa id de la superclase que es el isbn
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
