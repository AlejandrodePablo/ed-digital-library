package com.iesam.digitallibrary.features.loan.domain;

import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.user.domain.User;

public class Loan {

    public final String loanId;
    public final User userid;
    public final DigitalResource digitalResource;
    public final String startDate;
    public final String returnDate;

    public Loan(String loanId, User userid, DigitalResource digitalResource, String startDate, String returnDate) {
        this.loanId = loanId;
        this.userid = userid;
        this.digitalResource = digitalResource;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
    public Loan(User userid, DigitalResource digitalResource, String startDate, String returnDate) {
        this.loanId = generateUniqueId();
        this.userid = userid;
        this.digitalResource = digitalResource;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    private static String generateUniqueId() {
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", userid=" + userid +
                ", eBookIsbn=" + digitalResource +
                ", startDate='" + startDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
