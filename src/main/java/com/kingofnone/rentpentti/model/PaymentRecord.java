package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PaymentRecord {
    @OneToOne
    private Payment payment;
}
