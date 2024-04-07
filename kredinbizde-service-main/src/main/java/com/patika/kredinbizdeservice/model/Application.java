package com.patika.kredinbizdeservice.model;


import com.patika.kredinbizdeservice.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class Application {

    private Loan loan;
    private Product product;
    private User user;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;

    public Application() {
    }

    /*
    public Application(CreditCard creditCard, User user, LocalDateTime localDateTime) {
        this.creditCard = creditCard;
        this.user = user;
        this.localDateTime = localDateTime;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }*/

    public Application(Product product, User user, LocalDateTime createDate) {
        this.product = product;
        this.user = user;
        this.createDate = createDate;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }

    public Application(Loan loan, User user, LocalDateTime createDate) {
        this.loan = loan;
        this.user = user;
        this.createDate = createDate;
        this.applicationStatus = ApplicationStatus.INITIAL;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setLocalDateTime(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        return "Application{" +
                "loan=" + loan +
                ", user=" + user +
                ", createDate=" + createDate +
                ", applicationStatus=" + applicationStatus +
                '}';
    }
}
