package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class PaymentParty extends BaseEntity {
    private String name;
    @OneToMany
    private Set<Person> responsiblePersonnel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getResponsiblePersonnel() {
        return responsiblePersonnel;
    }

    public void setResponsiblePersonnel(Set<Person> responsiblePersonnel) {
        this.responsiblePersonnel = responsiblePersonnel;
    }
}
