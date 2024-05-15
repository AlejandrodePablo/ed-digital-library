package com.iesam.digitallibrary.features.ebook.presentation;

import com.iesam.digitallibrary.features.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.ebook.domain.*;

import java.util.List;
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
        System.out.println("eBook ISBN to delete: ");
        String isbn = scanner.nextLine();
        DeleteEBookUseCase deleteEBookUseCase = new DeleteEBookUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        deleteEBookUseCase.execute(isbn);
    }

    public static void getEBook() {
        System.out.println("eBook ISBN to list: ");
        String isbn = scanner.nextLine();

        GetEBookUseCase getEBookUseCase = new GetEBookUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        EBook eBook = getEBookUseCase.execute(isbn);

        if (eBook != null) {
            System.out.println(eBook.toString());
        } else {
            System.out.println("eBook with ISBN " + isbn + " does not exist");
        }
    }

    public static void getEbooks() {
        ListEbooksUseCase listEbooksUseCase = new ListEbooksUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        List<EBook> ebooks = listEbooksUseCase.execute();
        System.out.println(ebooks);
    }

    public static void updateEBook() {
        System.out.println("ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();
        System.out.println("Nuevo titulo: ");
        String title = scanner.nextLine();
        System.out.println("Nuevo autor: ");
        String author = scanner.nextLine();
        System.out.println("Nuevo genero: ");
        String genre = scanner.nextLine();
        System.out.println("Nuevo año de publicacion: ");
        String publicationYear = scanner.nextLine();
        System.out.println("Nuevo idioma: ");
        String language = scanner.nextLine();

        EBook updatedEBook = new EBook(isbn, title, author, genre, publicationYear, language);
        UpdateEBookUseCase updateEBookUseCase = new UpdateEBookUseCase(new EBookDataRepository(new EBookFileLocalDataSource()));
        updateEBookUseCase.execute(updatedEBook);
    }
}
