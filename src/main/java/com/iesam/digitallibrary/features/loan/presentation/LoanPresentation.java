package com.iesam.digitallibrary.features.loan.presentation;

import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.Loan;
import com.iesam.digitallibrary.features.loan.domain.NewLoanUseCase;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoanPresentation {

    static Scanner scanner = new Scanner(System.in);

    public static void createLoan() {

        System.out.println("Loan ID: ");
        String loanId = scanner.nextLine();

        System.out.println("User ID: ");
        User user = UserPresentation.getUser();

        System.out.println("EBook ISBN: ");
        EBook eBook = EBookPresentation.getEBook();

        System.out.println("Loan date (YYYY-MM-DD): ");
        Date loanDate = readDate();

        System.out.println("Loan return date (YYYY-MM-DD): ");
        Date loanReturnDate = readDate();

        System.out.println("Loan status");
        String status = scanner.nextLine();

        Loan newLoan = new Loan(loanId, user, eBook, loanDate, loanReturnDate, status);
        NewLoanUseCase newLoanUseCase = new NewLoanUseCase(new LoanDataRepository(new LoanFileLocalDataSource()));
        newLoanUseCase.execute(newLoan);
    }

    private static Date readDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        while (date == null) {
            try {
                String dateString = scanner.nextLine();
                date = formatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Incorrect date format. Please enter the date in the format YYYY-MM-DD: ");
            }
        }
        return date;
    }
}
