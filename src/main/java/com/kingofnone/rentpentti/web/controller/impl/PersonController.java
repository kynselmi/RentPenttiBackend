package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.service.model.impl.PersonModelService;
import com.kingofnone.rentpentti.model.Person;
import org.springframework.web.bind.annotation.*;

@RestController("personController")
@RequestMapping("/person")
public class PersonController extends AbstractController<Person> {
    public PersonController() {
        setService(new PersonModelService());
    }
}
