package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends AbstractDao {
    public PersonDao() {
        setClazz(Person.class);
    }
}
