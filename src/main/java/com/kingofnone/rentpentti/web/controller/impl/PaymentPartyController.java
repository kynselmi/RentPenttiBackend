package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.Person;
import com.kingofnone.rentpentti.service.impl.PaymentPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class PaymentPartyController extends AbstractController<PaymentParty> {
    @Autowired
    private PaymentPartyService paymentPartyService;

    public PaymentPartyController() {
        setService(paymentPartyService);
    }

    @GetMapping(value = "/{id}/personnel", produces = ControllerConstants.APPLICATION_JSON)
    public Set<Person> getPersonnel(@PathVariable long id) {
        Optional<PaymentParty> paymentParty = paymentPartyService.getById(id);
        return paymentParty.isPresent() ? paymentParty.get().getResponsiblePersonnel() : Collections.emptySet();
    }

    @PostMapping(value = "/{id}/personnel", produces = ControllerConstants.APPLICATION_JSON)
    public ResponseEntity<Person> addPaymentPersonnel(@PathVariable long id, @RequestBody Set<Long> bandMemberIds) {
        paymentPartyService.addPersonnel(id, bandMemberIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
