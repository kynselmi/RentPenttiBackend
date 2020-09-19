package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class MonthlyPayment extends Payment {
    private Date nextDueDate;
}
