package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.PaymentParty;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class PaymentPartyDao extends AbstractDao<PaymentParty> {
    public PaymentPartyDao() { setClazz(PaymentParty.class); }
}
