package com.iesam.digitallibrary.features.loan.presentation;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.*;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;


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
            System.out.println("Menu de Gestión de prestamos:");
            System.out.println("1. Agregar prestamos");
            System.out.println("2. Eliminar prestamo");
            System.out.println("3. Mostrar Todos los prestamos activos");
            System.out.println("4. Mostrar todos los prestamos finalizados");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createLoan();
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

    public static void createLoan() {

        System.out.println("Loan ID: ");
        String loanId = scanner.nextLine();

        System.out.println("User ID: ");
        User user = UserPresentation.getUser();

        System.out.println("EBook ISBN: ");
        EBook eBook = EBookPresentation.getEBook();

        System.out.println("Start date to loan (today): ");
        Date loanStartDate = generateCurrentDate();
        String startDate = simpleDateFormat.format(loanStartDate);

        System.out.println("Loan return date (YYYY-MM-DD): ");
        Date loanReturnDate = generateDateTenDaysAhead();
        String returnDate = simpleDateFormat.format(loanReturnDate);

        Loan newLoan = new Loan(loanId, user, eBook, startDate, returnDate);
        NewLoanUseCase newLoanUseCase = new NewLoanUseCase(new LoanDataRepository(
                LoanFileLocalDataSource.getInstance()));
        newLoanUseCase.execute(newLoan);
    }

    private static Date generateCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Date generateDateTenDaysAhead() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void getUnreturnedLoans() {

        ListUnreturnedLoansUseCase listUnreturnedLoansUseCase = new ListUnreturnedLoansUseCase(new LoanDataRepository(LoanFileLocalDataSource.getInstance()));
        List<Loan> unreturnedLoans = listUnreturnedLoansUseCase.execute();

        for (Loan loan : unreturnedLoans) {
            System.out.println("Unreturned loans");
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
            System.out.println("Returned loans");
            System.out.println(loan.toString());
        }
    }

}
