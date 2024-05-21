package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.user.domain.User;

public class Loan {

    public final String loanId;
    public final User userid;
    public final EBook eBookIsbn;
    public final String startDate;
    public final String returnDate;

    public Loan(String loanId, User userid, EBook eBookIsbn, String startDate, String returnDate) {
        this.loanId = loanId;
        this.userid = userid;
        this.eBookIsbn = eBookIsbn;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
}
