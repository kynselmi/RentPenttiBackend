package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.service.impl.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("paymentController")
@RequestMapping("/payment")
public class PaymentController extends AbstractController<Payment> {
    public PaymentController() {
        setService(new PaymentService());
    }
}
