package com.iesam.digitallibrary.features.loan.presentation;

import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.ListUnreturnedLoansUseCase;
import com.iesam.digitallibrary.features.loan.domain.Loan;
import com.iesam.digitallibrary.features.loan.domain.NewLoanUseCase;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanPresentation {

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
}
