package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

public class NewLoanUseCase {
    private LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final EBookRepository eBookRepository;

    public NewLoanUseCase(LoanRepository loanRepository, UserRepository userRepository, EBookRepository eBookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.eBookRepository = eBookRepository;
    }

    public boolean execute(String loanId, String id, String isbn, String startDate, String returnDate) {
        User user = userRepository.getUser(id);
        EBook eBook = eBookRepository.getEBook(isbn);

        if (user == null || eBook == null) {
            return false;
        }

        Loan newLoan = new Loan(loanId, user, eBook, startDate, returnDate);
        loanRepository.createLoan(newLoan);
        return true;
    }

}
