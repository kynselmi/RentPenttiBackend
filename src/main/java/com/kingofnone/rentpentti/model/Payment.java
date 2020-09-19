package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Payment extends BaseEntity {
    @OneToOne
    private PaymentParty payer;
    @OneToOne
    private PaymentParty receiver;
    private BigDecimal amount;
    @OneToOne
    private PaymentRecord paymentRecord;
}
