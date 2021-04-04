package com.kingofnone.rentpentti.model.factory.impl;

import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.model.factory.Factory;

public class PaymentFactory implements Factory<Payment> {
    public Payment create() {
        return new Payment();
    }
}
