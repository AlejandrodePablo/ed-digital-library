package com.iesam.digitallibrary.features.digitalresource.audiobook.presentation;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.AudiobookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.*;
import com.iesam.digitallibrary.features.loan.domain.Loan;

import java.util.List;
import java.util.Scanner;

public class AudiobookPresentation {

    static Scanner scanner = new Scanner(System.in);

    public void showAudiobookMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Gestión de Audiobook:");
            System.out.println("1. Agregar Audiobook");
            System.out.println("2. Borrar Audiobook por ISBN");
            System.out.println("3. Mostrar todos los Audiobook");
            System.out.println("4. Mostrar Audiobook por ISBN");
            System.out.println("5. Actualizar Audiobook por ISBN");
            System.out.println("6. Volver al Menú de Gestion de Recursos Digitales");
            System.out.print("Ingrese su opción: \n");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createAudiobook();
                    break;
                case 2:
                    deleteAudiobook();
                    break;
                case 3:
                    getAudiobooks();
                    break;
                case 4:
                    getAudiobook();
                    break;
                case 5:
                    updateAudiobook();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú de Recursos Digitales...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 6);
    }

    public static void createAudiobook(){

        System.out.println("Title: ");
        scanner.nextLine();
        String title = scanner.nextLine();
        System.out.println("Author(s): ");
        String author = scanner.nextLine();
        System.out.println("Genre: ");
        String genre = scanner.nextLine();
        System.out.println("Publication Year: ");
        String publicationYear = scanner.nextLine();
        System.out.println("Duration (minutes): ");
        String duration = scanner.nextLine();

        Audiobook newAudiobook = new Audiobook(title, author, genre, publicationYear, duration);
        NewAudiobookUseCase newAudiobookUseCase = new NewAudiobookUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        newAudiobookUseCase.execute(newAudiobook);
    }

    public static void getAudiobooks(){
        ListAudiobooksUseCase listAudiobooksUseCase = new ListAudiobooksUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        List<Audiobook> audiobooks = listAudiobooksUseCase.execute();
        for (Audiobook audiobook : audiobooks) {
            System.out.println(audiobook.toString());
        }
    }

    public static Audiobook getAudiobook(){
        System.out.println("ISBN of Audiobook to list: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();

        GetAudiobookUseCase getAudiobookUseCase = new GetAudiobookUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        try{Audiobook audiobook = getAudiobookUseCase.execute(isbn);
            System.out.println(audiobook);
            return audiobook;
        } catch (AudiobookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void deleteAudiobook(){
        System.out.println("ISBN of Audiobook to delete: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();

        DeleteAudiobookUseCase deleteAudiobookUseCase = new DeleteAudiobookUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        try{
            deleteAudiobookUseCase.execute(isbn);
            System.out.println("Audiolibro Borrado con exito");
        }catch (AudiobookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateAudiobook(){
        System.out.println("ISBN of Audiobook to update: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();

        // Solicitar al usuario los nuevos detalles del audiolibro
        System.out.println("Enter new information for the Audiobook:");
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

        Audiobook updatedAudiobook = new Audiobook(isbn, title, author, genre, publicationYear, duration);

        UpdateAudiobookUseCase updateAudiobookUseCase = new UpdateAudiobookUseCase(new AudiobookDataRepository(new AudiobookFileLocalDataSource()));
        try {
            updateAudiobookUseCase.execute(isbn, updatedAudiobook);
            System.out.println("Audiobook updated successfully.");
        } catch (AudiobookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
