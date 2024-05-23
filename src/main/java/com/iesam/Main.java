package com.iesam;

import com.iesam.digitallibrary.features.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.loan.domain.Loan;
import com.iesam.digitallibrary.features.loan.presentation.LoanPresentation;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar eBooks");
            System.out.println("3. Gestionar Préstamos");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    UserPresentation userPresentation = new UserPresentation();
                    userPresentation.showUserMenu();
                    break;
                case 2:
                    EBookPresentation ebookPresentation = new EBookPresentation();
                    ebookPresentation.showEbookMenu();
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