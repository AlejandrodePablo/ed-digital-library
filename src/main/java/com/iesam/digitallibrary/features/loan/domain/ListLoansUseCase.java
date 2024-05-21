package com.iesam.digitallibrary.features.loan.domain;

import java.util.List;

public class ListLoansUseCase {
    private LoanRepository loanRepository;

    public ListLoansUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> execute() {
        return loanRepository.getLoans();
    }

}
