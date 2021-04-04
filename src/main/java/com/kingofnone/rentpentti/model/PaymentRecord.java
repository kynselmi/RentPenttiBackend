package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class PaymentRecord extends BaseEntity {
    @OneToOne
    private Payment payment;
    private Date payedDate;

    public boolean isPayed() {
        if (payedDate == null) {
            return false;
        }
        return payedDate.before(new Date(System.currentTimeMillis()*1000));
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }
}
