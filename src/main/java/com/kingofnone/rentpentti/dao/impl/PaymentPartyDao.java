package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.PaymentParty;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentPartyDao extends AbstractDao<PaymentParty> {
    public PaymentPartyDao() { setClazz(PaymentParty.class); }
}
