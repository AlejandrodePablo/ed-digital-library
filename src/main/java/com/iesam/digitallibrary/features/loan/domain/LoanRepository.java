package com.iesam.digitallibrary.features.loan.domain;

import java.util.List;

public interface LoanRepository {

    void createLoan(Loan loan);

    List<Loan> getLoans();

    void deleteLoan(String id);

}
