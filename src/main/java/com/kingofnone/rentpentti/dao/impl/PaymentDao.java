package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao extends AbstractDao {
    public PaymentDao() { setClazz(Payment.class); }
}
