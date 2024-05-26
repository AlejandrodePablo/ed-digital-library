package com.iesam.digitallibrary.features.loan.presentation;

import com.iesam.digitallibrary.features.digitalresource.audiobook.data.AudiobookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.audiobook.data.local.AudiobookFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.EBookDataRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.*;
import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanPresentation {

    public void showLoanMenu() {
        int opcion;
        do {
            System.out.println("\nMenu de Gestión de prestamos:");
            System.out.println("1. Agregar prestamos de Libros Digitales");
            System.out.println("2. Eliminar prestamo");
            System.out.println("3. Mostrar Todos los prestamos activos");
            System.out.println("4. Mostrar todos los prestamos finalizados");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Ingrese su opción: \n");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createEBookLoan();
                    break;
                case 2:
                    deleteLoan();
                    break;
                case 3:
                    getUnreturnedLoans();
                    break;
                case 4:
                    getReturnedLoans();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 5);
    }


    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void createEBookLoan() {

        System.out.println("User ID: ");
        scanner.nextLine();
        String userId = scanner.nextLine();

        System.out.println("Digital Resource ISBN: ");
        String drISBN = scanner.nextLine();

        Date loanStartDate = generateCurrentDate();
        String startDate = simpleDateFormat.format(loanStartDate);


        Date loanReturnDate = generateDateTenDaysAhead();
        String returnDate = simpleDateFormat.format(loanReturnDate);

        NewLoanUseCase newLoanUseCase = new NewLoanUseCase(

                new LoanDataRepository(LoanFileLocalDataSource.getInstance()),
                new UserDataRepository(UserMemLocalDataSource.getInstance(), UserFileLocalDataSource.getInstance()),
                new EBookDataRepository(EBookFileLocalDataSource.getInstance()),
                new AudiobookDataRepository(new AudiobookFileLocalDataSource())

        );

        boolean success = newLoanUseCase.execute(userId, drISBN, startDate, returnDate);

        if (success) {
            System.out.println("Loan created successfully.");
        } else {
            System.out.println("Error: User or digital resource not found.");
        }

    }

    private static Date generateCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Date generateDateTenDaysAhead() {
        LocalDate futureDate = LocalDate.now().plusDays(10);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void getUnreturnedLoans() {

        ListUnreturnedLoansUseCase listUnreturnedLoansUseCase = new ListUnreturnedLoansUseCase(new LoanDataRepository(LoanFileLocalDataSource.getInstance()));
        List<Loan> unreturnedLoans = listUnreturnedLoansUseCase.execute();

        for (Loan loan : unreturnedLoans) {
            System.out.println(loan.toString());
        }
    }

    public static void deleteLoan() {
        System.out.println("ID loan to delete");
        String id = scanner.nextLine();
        DeleteLoanUseCase deleteLoanUseCase = new DeleteLoanUseCase(new LoanDataRepository(LoanFileLocalDataSource.getInstance()));
        deleteLoanUseCase.execute(id);
    }

    public static void getReturnedLoans() {
        ListReturnedLoansUseCase listReturnedLoansUseCase = new ListReturnedLoansUseCase(new LoanDataRepository(LoanFileLocalDataSource.getInstance()));
        List<Loan> returnedLoans = listReturnedLoansUseCase.execute();

        for (Loan loan : returnedLoans) {
            System.out.println(loan.toString());
        }
    }

}
