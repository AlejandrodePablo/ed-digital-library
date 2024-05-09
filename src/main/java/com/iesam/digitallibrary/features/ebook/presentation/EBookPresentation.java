package com.iesam.digitallibrary.features.ebook.presentation;

import com.iesam.digitallibrary.features.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.ebook.domain.DeleteEBookUseCase;
import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.ebook.domain.NewEBookUseCase;

import java.util.Scanner;


public class EBookPresentation {

    static Scanner scanner = new Scanner(System.in);

    public static void createEBook() {

        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Author(s): ");
        String author = scanner.nextLine();
        System.out.println("Genre: ");
        String genre = scanner.nextLine();
        System.out.println("Publication Year: ");
        String publicationYear = scanner.nextLine();
        System.out.println("Language: ");
        String language = scanner.nextLine();

        EBook newEBook = new EBook(isbn, title, author, genre, publicationYear, language);
        NewEBookUseCase newEBookUseCase = new NewEBookUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        newEBookUseCase.execute(newEBook);
    }

    public static void deleteEBook() {
        System.out.println("ISBN del eBook a eliminar: ");
        String isbn = scanner.nextLine();
        DeleteEBookUseCase deleteEBookUseCase = new DeleteEBookUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        deleteEBookUseCase.execute(isbn);
    }
}