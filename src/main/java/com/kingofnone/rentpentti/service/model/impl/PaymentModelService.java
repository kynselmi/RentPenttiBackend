package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.PaymentDao;
import com.kingofnone.rentpentti.model.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentModelService extends AbstractModelService<Payment> {
    public PaymentModelService() {
        setDao(new PaymentDao());
    }
}
