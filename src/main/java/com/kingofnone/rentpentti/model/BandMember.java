package com.kingofnone.rentpentti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class BandMember extends Person {

    @ManyToMany(targetEntity = Band.class, mappedBy = "members")
    @JsonIgnore
    private Set<Band> bands;

    public BandMember() {}

    public BandMember(String firstName, String lastName, Set<Band> bands) {
        super(firstName, lastName);
        this.bands = bands;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }
}
