package com.kingofnone.rentpentti.model.factory.impl;

import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.factory.Factory;

public class PaymentPartyFactory implements Factory<PaymentParty> {
    public PaymentParty create() { return new PaymentParty(); }
}
