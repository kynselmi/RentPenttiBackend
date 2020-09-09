package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PaymentParty extends BaseEntity {
    private String name;
    @OneToMany
    private List<Person> responsiblePersonnel;
}
