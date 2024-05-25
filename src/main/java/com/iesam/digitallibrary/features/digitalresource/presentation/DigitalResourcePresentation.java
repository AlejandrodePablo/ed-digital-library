package com.iesam.digitallibrary.features.digitalresource.presentation;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.AudiobookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.data.DigitalResourceDataRepository;
import com.iesam.digitallibrary.features.digitalresource.data.local.DigitalResourceFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.digitalresource.domain.ListDigitalResourcesUseCase;
import com.iesam.digitallibrary.features.digitalresource.audiobook.presentation.AudiobookPresentation;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.ebook.presentation.EBookPresentation;

import java.util.List;
import java.util.Scanner;

public class DigitalResourcePresentation {

    static Scanner scanner = new Scanner(System.in);
    public void showDResourceMenu() {
        int opcion;
        do {
            System.out.println("Menú de Gestión de Recursos Digitales:");
            System.out.println("1. Gestionar eBook");
            System.out.println("2. Gestionar Audiobook");
            System.out.println("3. Mostrar Todos los Recursos Digitales");
            System.out.println("4. Volver al menú de opciones");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    EBookPresentation eBookPresentation = new EBookPresentation();
                    eBookPresentation.showEbookMenu();
                    break;
                case 2:
                    AudiobookPresentation audiobookPresentation = new AudiobookPresentation();
                    audiobookPresentation.showAudiobookMenu();
                    break;
                case 3:
                    getDigitalResources();
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 4);
    }
    public static void getDigitalResources() {
        ListDigitalResourcesUseCase listDigitalResourcesUseCase = new ListDigitalResourcesUseCase(new DigitalResourceDataRepository(new DigitalResourceFileLocalDataSource()),
                new EBookDataRepository(new EBookFileLocalDataSource()),
                new AudiobookDataRepository(new AudiobookFileLocalDataSource())
        );

        List<DigitalResource> digitalResources = listDigitalResourcesUseCase.execute();
        for (Object resource : digitalResources) {
            System.out.println(resource);
        }
    }

}
