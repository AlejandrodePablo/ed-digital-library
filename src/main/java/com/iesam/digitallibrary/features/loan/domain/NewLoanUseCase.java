package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.AudiobookRepository;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResourceRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

public class NewLoanUseCase {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final EBookRepository eBookRepository;
    private final AudiobookRepository audiobookRepository;

    public NewLoanUseCase(LoanRepository loanRepository, UserRepository userRepository, EBookRepository eBookRepository, AudiobookRepository audiobookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.eBookRepository = eBookRepository;
        this.audiobookRepository = audiobookRepository;
    }

    public boolean execute(String loanId, String id, String isbn, String startDate, String returnDate) {
        User user = userRepository.getUser(id);
        EBook ebook = eBookRepository.getEBook(isbn);
        Audiobook audiobook = audiobookRepository.getAudiobook(isbn);

        if (ebook != null) {
            Loan newLoan = new Loan(loanId, user, ebook, startDate, returnDate);
            loanRepository.createLoan(newLoan);
            return true;
        }else {
            Loan newLoan = new Loan(loanId, user, audiobook, startDate, returnDate);
            loanRepository.createLoan(newLoan);
            return true;
        }


    }

}
