package com.kingofnone.rentpentti.model.factory.impl;

import com.kingofnone.rentpentti.model.PaymentRecord;
import com.kingofnone.rentpentti.model.factory.Factory;

public class PaymentRecordFactory implements Factory<PaymentRecord> {
    @Override
    public PaymentRecord create() { return new PaymentRecord(); }
}
