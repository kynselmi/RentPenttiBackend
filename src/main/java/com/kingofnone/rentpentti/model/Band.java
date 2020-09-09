package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Band extends BaseEntity {
    @ManyToMany(targetEntity = BandMember.class, mappedBy = "bands")
    private Set<BandMember> members;

    public Set<BandMember> getMembers() {
        return members;
    }

    public void setMembers(Set<BandMember> members) {
        this.members = members;
    }
}
