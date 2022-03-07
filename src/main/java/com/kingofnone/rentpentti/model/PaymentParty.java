package com.kingofnone.rentpentti.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PaymentParty extends BaseEntity {

    @Column(unique = true)
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
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
