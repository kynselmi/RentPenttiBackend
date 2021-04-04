package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.Person;
import com.kingofnone.rentpentti.service.model.impl.PaymentPartyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController("paymentPartyController")
@RequestMapping("/paymentparty")
public class PaymentPartyController extends AbstractController<PaymentParty> {
    @Autowired
    private PaymentPartyModelService paymentPartyService;

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
