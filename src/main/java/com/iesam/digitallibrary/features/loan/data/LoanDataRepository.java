package com.iesam.digitallibrary.features.loan.data;

import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitallibrary.features.loan.domain.Loan;
import com.iesam.digitallibrary.features.loan.domain.LoanRepository;

import java.util.List;

public class LoanDataRepository implements LoanRepository {

    LoanFileLocalDataSource loanFileLocalDataSource = new LoanFileLocalDataSource();

    public LoanDataRepository(LoanFileLocalDataSource loanFileLocalDataSource) {
    }

    @Override
    public void createLoan(Loan loan) {
        loanFileLocalDataSource.save(loan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanFileLocalDataSource.findAll();
    }

    @Override
    public void deleteLoan(String id) {
        this.loanFileLocalDataSource.delete(id);
    }
}
