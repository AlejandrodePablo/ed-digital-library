package com.iesam.digitallibrary.features.loan.domain;

public class NewLoanUseCase {
    private LoanRepository loanRepository;

    public NewLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Loan loan){
        loanRepository.createLoan(loan);
    }
}
