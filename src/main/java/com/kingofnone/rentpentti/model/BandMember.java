package com.kingofnone.rentpentti.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class BandMember extends Person {
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private List<Band> bands;

    public BandMember() {}

    public BandMember(String firstName, String lastName, List<Band> bands) {
        super(firstName, lastName);
        this.bands = bands;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }
}
