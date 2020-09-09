package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.BaseEntity;
import com.kingofnone.rentpentti.service.Service;
import com.kingofnone.rentpentti.web.controller.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AbstractController<T extends BaseEntity> implements Controller<T> {

    protected Service<T> service;

    @Override
    public List<T> findAll() {
        return service.findAllActive();
    }

    @Override
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @Override
    public ResponseEntity<T> getById(@PathVariable long id) {
        Optional optional = service.getById(id);
        HttpHeaders headers = new HttpHeaders();
        if (!optional.isPresent()) {
            return new ResponseEntity<>(null, headers, HttpStatus.OK);
        }
        T entity = (T)optional.get();
        return entity.isNotDeleted() ? new ResponseEntity<>(entity, headers, HttpStatus.OK) : new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public T update(@PathVariable long id, @RequestBody T entity) {
        return service.updateById(id, entity);
    }

    @Override
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }

    public Service<T> getService() {
        return service;
    }

    public void setService(Service<T> service) {
        this.service = service;
    }
}

