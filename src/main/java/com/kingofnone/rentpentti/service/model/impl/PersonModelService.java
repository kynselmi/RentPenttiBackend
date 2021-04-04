package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.PersonDao;
import com.kingofnone.rentpentti.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<Person> {
    public PersonService() {
        setDao(new PersonDao());
    }
}
