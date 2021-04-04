package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import java.time.Month;
import java.time.Year;
import java.util.Date;


@Entity
public class MonthlyPayment extends Payment {
    private Month month;
    private Year year;
    private Date nextDueDate;

    public Date getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(Date nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public boolean before(MonthlyPayment monthlyPayment) {
        if (this.year.isBefore(monthlyPayment.year)) {
            return true;
        }
        if (this.month.compareTo(monthlyPayment.month) > 0) {
            return true;
        }
        return false;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
