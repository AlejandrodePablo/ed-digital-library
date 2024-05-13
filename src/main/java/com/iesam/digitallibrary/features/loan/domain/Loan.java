package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.user.domain.User;

import java.util.Date;

public class Loan {

    public final String loanId;
    public final User userid;
    public final EBook eBookIsbn;
    public final Date startDate;
    public final Date dueDate;
    public final String status;

    public Loan(String loanId, User userid, EBook eBookIsbn,Date startDate, Date dueDate, String status) {
        this.loanId = loanId;
        this.userid = userid;
        this.eBookIsbn = eBookIsbn;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }
}
