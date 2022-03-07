package com.kingofnone.rentpentti.model;

import javax.persistence.*;

import java.util.Set;

@Entity
public class Band extends BaseEntity implements Model {

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private Set<BandMember> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BandMember> getMembers() {
        return members;
    }

    public void setMembers(Set<BandMember> members) {
        this.members = members;
    }
}
