package com.iesam;


import com.iesam.digitallibrary.features.digitalresource.presentation.DigitalResourcePresentation;
import com.iesam.digitallibrary.features.loan.presentation.LoanPresentation;
import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.UserRepository;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Recursos Digitales");
            System.out.println("3. Gestionar Préstamos");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: \n");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    UserRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
                    UserPresentation userPresentation = new UserPresentation(userRepository);
                    userPresentation.showUserMenu();
                    break;

                case 2:
                    DigitalResourcePresentation digitalResourcePresentation = new DigitalResourcePresentation();
                    digitalResourcePresentation.showDResourceMenu();
                    break;
                case 3:
                    LoanPresentation loanPresentation = new LoanPresentation();
                    loanPresentation.showLoanMenu();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 4);
        scanner.close();
    }
}