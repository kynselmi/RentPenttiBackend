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

    @Override
    public String toString() {
        return """
                Payer: %s
                Receiver: %s
                Amount: %s
                Payment record: %s
                """.formatted(payer.getName(), receiver.getName(), amount.toString(), paymentRecord);
    }

    public PaymentParty getPayer() {
        return payer;
    }

    public void setPayer(PaymentParty payer) {
        this.payer = payer;
    }

    public PaymentParty getReceiver() {
        return receiver;
    }

    public void setReceiver(PaymentParty receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentRecord getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }
}
