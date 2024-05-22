package com.iesam.digitallibrary.features.loan.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListReturnedLoansUseCase {

    private LoanRepository loanRepository;

    public ListReturnedLoansUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> execute() {

        List<Loan> loans = loanRepository.getLoans();
        List<Loan> unreturnedLoans = new ArrayList<>();

        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Loan loan : loans) {
            try {
                Date loanReturnDate = dateFormat.parse(loan.returnDate);

                if (loanReturnDate.before(currentDate)) {
                    unreturnedLoans.add(loan);
                }
            } catch (ParseException e) {
                System.out.println("Error al parsear la fecha de retorno del préstamo con ID " + loan.loanId + ": " + e.getMessage());
            }
        }

        return unreturnedLoans;
    }
}
