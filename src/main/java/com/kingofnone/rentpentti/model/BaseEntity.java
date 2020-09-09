package com.kingofnone.rentpentti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "deleted", columnDefinition = "Bit(1) default false")
    private boolean deleted = false;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdTime")
    private Date createdTime;

    @Column(name = "lastModifiedBy")
    private String lastModifiedBy;

    @Column(name = "modifiedTime", nullable = false)
    private Date modifiedTime;

    @PrePersist
    protected void prePersist() {
        if (this.createdTime == null) createdTime = new Date();
        if (this.modifiedTime == null) modifiedTime = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.modifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.modifiedTime = new Date();
    }

    @JsonIgnore
    public boolean isDeleted() {
        return deleted;
    }

    @JsonIgnore
    public boolean isNotDeleted() {
        return !deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
