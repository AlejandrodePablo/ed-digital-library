package com.iesam.digitallibrary.features.digitalresource.audiobook.presentation;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.AudiobookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.NewAudiobookUseCase;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;

import java.util.Scanner;

public class AudiobookPresentation {

    static Scanner scanner = new Scanner(System.in);

    public void showAudiobookMenu() {
        int opcion;
        do {
            System.out.println("Menú de Gestión de Audiobook:");
            System.out.println("1. Agregar Audiobook");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createAudiobook();
                    break;

                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 6);
    }

    public static void createAudiobook(){
        System.out.println("ISBN: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Author(s): ");
        String author = scanner.nextLine();
        System.out.println("Genre: ");
        String genre = scanner.nextLine();
        System.out.println("Publication Year: ");
        String publicationYear = scanner.nextLine();
        System.out.println("Duration (minutes): ");
        String duration = scanner.nextLine();

        Audiobook newAudiobook = new Audiobook(isbn, title, author, genre, publicationYear, duration);
        NewAudiobookUseCase newAudiobookUseCase = new NewAudiobookUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        newAudiobookUseCase.execute(newAudiobook);
    }

}
