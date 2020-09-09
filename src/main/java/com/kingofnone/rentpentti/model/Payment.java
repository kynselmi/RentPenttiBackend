package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment extends BaseEntity{
    @OneToOne
    private PaymentParty payer;
    @OneToOne
    private PaymentParty receiver;
    private BigDecimal amount;
    private Date payedTime;
}
