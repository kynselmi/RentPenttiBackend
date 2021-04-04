package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.PaymentDao;
import com.kingofnone.rentpentti.model.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("paymentService")
public class PaymentService extends AbstractService<Payment> {
    @Resource
    PaymentDao dao;
    public PaymentService() {
        setDao(new PaymentDao());
    }
}
