package com.iesam.digitallibrary.features.digitalresource.ebook.presentation;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.*;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;


import java.util.List;
import java.util.Scanner;


public class EBookPresentation {

    static Scanner scanner = new Scanner(System.in);

    public void showEbookMenu() {
        int opcion;
        do {
            System.out.println("Menú de Gestión de eBook:");
            System.out.println("1. Agregar eBook");
            System.out.println("2. Eliminar eBook");
            System.out.println("3. Mostrar Todos los eBook");
            System.out.println("4. Mostrar eBook por ISBN");
            System.out.println("5. Actualizar un eBook");
            System.out.println("6. Volver al de Gestion de Recursos Digitales");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createEBook();
                    break;
                case 2:
                    deleteEBook();
                    break;
                case 3:
                    getEbooks();
                    break;
                case 4:
                    getEBook();
                    break;
                case 5:
                    updateEBook();
                case 6:
                    System.out.println("Volviendo al Menú de Recursos Digitales...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 6);
    }


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
        NewEBookUseCase newEBookUseCase = new NewEBookUseCase(new EBookDataRepository(EBookFileLocalDataSource.getInstance()));
        newEBookUseCase.execute(newEBook);
    }

    public static void deleteEBook() {
        System.out.println("eBook ISBN to delete: ");
        String isbn = scanner.nextLine();
        DeleteEBookUseCase deleteEBookUseCase = new DeleteEBookUseCase(new EBookDataRepository(EBookFileLocalDataSource.getInstance()));
        deleteEBookUseCase.execute(isbn);
    }

    public static EBook getEBook() {
        System.out.println("eBook ISBN to list: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();

        GetEBookUseCase getEBookUseCase = new GetEBookUseCase(new EBookDataRepository(EBookFileLocalDataSource.getInstance()
        ));
        EBook eBook = getEBookUseCase.execute(isbn);

        if (eBook != null) {
            System.out.println(eBook.toString());
        } else {
            System.out.println("eBook with ISBN " + isbn + " does not exist");
        }
        return eBook;
    }

    public static void getEbooks() {
        ListEbooksUseCase listEbooksUseCase = new ListEbooksUseCase(new EBookDataRepository(EBookFileLocalDataSource.getInstance()));
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
        UpdateEBookUseCase updateEBookUseCase = new UpdateEBookUseCase(new EBookDataRepository(EBookFileLocalDataSource.getInstance()));
        updateEBookUseCase.execute(updatedEBook);
    }
}
