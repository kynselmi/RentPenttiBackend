package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.service.model.impl.PaymentModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("paymentController")
@RequestMapping("/payment")
public class PaymentController extends AbstractController<Payment> {
    public PaymentController() {
        setService(new PaymentModelService());
    }
}
