package com.kingofnone.rentpentti.model.factory.impl;

import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryService {

    @Autowired
    private static final Factory<Payment> paymentFactory = new PaymentFactory();
    @Autowired
    private static final Factory<PaymentParty> paymentPartyFactory = new PaymentPartyFactory();

    public static Factory getFactory(Class clazz) {
        if (clazz.isInstance(Payment.class)) {
            return paymentFactory;
        }
        if (clazz.isInstance(PaymentParty.class)) {
            return paymentPartyFactory;
        }
        return null;
    }
}
