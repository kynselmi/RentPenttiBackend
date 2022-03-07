package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.PaymentDao;
import com.kingofnone.rentpentti.integration.payment.PaymentImporter;
import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.model.PaymentRecord;
import com.kingofnone.rentpentti.model.factory.Factory;
import com.kingofnone.rentpentti.model.factory.impl.FactoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentModelService extends AbstractModelService<Payment> {

    private static final Logger logger = LogManager.getLogger(PaymentModelService.class);

    @Autowired
    PaymentRecordModelService paymentRecordModelService;


    public PaymentModelService() {
        setDao(new PaymentDao());
    }

    @Override
    public Optional<Payment> create(Payment payment) {
        Optional<Payment> paymentOptional = super.create(payment);
        if (paymentOptional.isEmpty()) {
            logger.error("Payment could not be created");
            return Optional.empty();
        }
        Payment newPayment = paymentOptional.get();
        Factory paymentRecordFactory = FactoryService.getFactory(PaymentRecord.class);
        if (paymentRecordFactory == null) {
            logger.error("No payment factory found for ["+PaymentRecord.class+"]");
            return Optional.empty();
        }
        PaymentRecord paymentRecord = (PaymentRecord) paymentRecordFactory.create();
        paymentRecord.setPayment(newPayment);
        paymentRecord.setPayedDate(new Date());

        paymentRecordModelService.create(paymentRecord);
        newPayment.setPaymentRecord(paymentRecord);
        return super.update(newPayment);
    }
}
