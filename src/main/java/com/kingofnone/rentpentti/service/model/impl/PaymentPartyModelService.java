package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.PaymentPartyDao;
import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentPartyModelService extends AbstractModelService<PaymentParty> {
    private static final Logger logger = LoggerFactory.getLogger(PaymentPartyModelService.class);

    public PaymentPartyModelService() {
        setDao(new PaymentPartyDao());
    }

    public void addPersonnel(Long paymentPartyId, Set<Long> personIdSet) {
        Optional<PaymentParty> paymentPartyOptional = this.dao.get(paymentPartyId);
        if (paymentPartyOptional.isEmpty()) {
            logger.error("PaymentParty with id: "+paymentPartyId+" not found");
            return;
        }
        PaymentParty paymentParty = paymentPartyOptional.get();
        paymentParty.setResponsiblePersonnel(personIdSet.stream()
                .map(id -> (Optional<Person>)dao.get(id))
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet()));
        dao.update(paymentParty);
    }

    public Optional<PaymentParty> getByName(String name) {
        return this.dao.findByAttribute("name", name);

    }
}
