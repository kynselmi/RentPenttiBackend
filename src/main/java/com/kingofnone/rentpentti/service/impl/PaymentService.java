package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.PaymentDao;
import com.kingofnone.rentpentti.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractService<Payment> {
    public PaymentService() {
        setDao(new PaymentDao());
    }
}
