package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.PersonDao;
import com.kingofnone.rentpentti.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonModelService extends AbstractModelService<Person> {
    public PersonModelService() {
        setDao(new PersonDao());
    }
}
